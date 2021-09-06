package net.novatech.jbserver.network.bedrock;

import java.net.InetAddress;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.novatech.jbprotocol.GameSession;
import net.novatech.jbprotocol.GameEdition;
import net.novatech.jbprotocol.ProtocolServer;
import net.novatech.jbprotocol.bedrock.BedrockSession;
import net.novatech.jbprotocol.bedrock.data.BedrockPong;
import net.novatech.jbprotocol.bedrock.packets.BedrockPacket;
import net.novatech.jbprotocol.listener.GameListener;
import net.novatech.jbprotocol.listener.LoginServerListener;
import net.novatech.jbprotocol.listener.ServerListener;
import net.novatech.jbprotocol.packet.AbstractPacket;
import net.novatech.jbprotocol.util.Pong;
import net.novatech.jbprotocol.util.SessionData;

import net.novatech.jbserver.event.player.*;
import net.novatech.jbserver.network.INetworkManager;
import net.novatech.jbserver.network.Network;
import net.novatech.jbserver.network.bedrock.retranslator.BedrockRetranslatorSector;
import net.novatech.jbserver.network.protocol.ProtocolInfo;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.player.PlayerInfo;
import net.novatech.jbserver.player.bedrock.BedrockPlayer;
import net.novatech.jbserver.player.bedrock.BedrockPlayerInfo;
import net.novatech.jbserver.server.Server;

import java.util.*;

public class BedrockNetworkManager implements INetworkManager{

	private Network network = null;
	private String ip = "0.0.0.0";
	private int port = 19132;
	public static ExecutorService pool = Executors.newCachedThreadPool();
	
	public BedrockNetworkManager(Network network, int port) {
		this.network = network;
		this.ip = network.getServer().getServerSettings().getAddress();
		this.port = port;
	}
	
	@Override
	public void start() {
		pool.execute(() -> {
			
			ProtocolServer protocol = new ProtocolServer(new InetSocketAddress(this.ip, this.port), GameEdition.BEDROCK);
			protocol.setMaxConnections(getNetwork().getServer().getServerSettings().getMaxPlayerCount());
			protocol.setServerListener(new ServerListener() {

				@Override
				public void sessionConnected(GameSession session) {
					BedrockSession bedrock = (BedrockSession) session;
					bedrock.requireAuthentication(network.getServer().getServerSettings().isOnlineModeEnabled());
					bedrock.setLoginListener(new LoginServerListener() {
						@Override
						public void loginCompleted(SessionData data) {
							PlayerInfo info = new BedrockPlayerInfo(data.getUsername(), 
									data.getAddress().getAddress().toString(),
									data.getAddress().getPort(),
									data.getUuid());
							Player player = new BedrockPlayer(new JBBedrockSession(bedrock), info);
							Server.getInstance().getFactoryManager().getPlayerFactory().addPlayer(player);
							
							PlayerLoginEvent login = new PlayerLoginEvent(player, info);
							login.call();
						}
					});
					bedrock.setGameListener(new GameListener() {

						@Override
						public void receivePacket(AbstractPacket packet) {
							Player player = Server.getInstance().getFactoryManager().getPlayerFactory().searchPlayerBySession(session);
							player.getSession().handleServerPacket(packet);
						}
						
					});
				}

				@Override
				public void sessionDisconnected(GameSession session, String cause) {
					Player p = Server.getInstance().getFactoryManager().getPlayerFactory().searchPlayerBySession(session);
					Server.getInstance().getFactoryManager().getPlayerFactory().removePlayer(p);
				}

				@Override
				public void handlePong(Pong pong) {
					BedrockPong p = (BedrockPong)pong;
					p.gameVersion = ProtocolInfo.BEDROCK_VERSION;
					p.protocolVersion = ProtocolInfo.BEDROCK_PROTOCOL;
					p.motd = Server.getInstance().getServerSettings().getMotd();
					p.maxPlayers = Server.getInstance().getServerSettings().getMaxPlayerCount();
					p.onlinePlayers = Server.getInstance().getFactoryManager().getPlayerFactory().getPlayerCount();
					p.subMotd = Server.getInstance().getServerSettings().getMotdUnderline();
					p.gamemode = Server.getInstance().getServerSettings().getDefaultGamemodeString();
				}
				
			});
			protocol.bind();
		});
	}

	@Override
	public void stop() {
		pool.shutdown();
	}
	
	public Network getNetwork() {
		return this.network;
	}
	
	@Override
	public int getPort() {
		return this.port;
	}
	
}

