package net.novatech.jbserver.network.java;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.novatech.jbprotocol.GameSession;
import net.novatech.jbprotocol.GameEdition;
import net.novatech.jbprotocol.ProtocolServer;
import net.novatech.jbprotocol.data.Pong;
import net.novatech.jbprotocol.data.SessionData;
import net.novatech.jbprotocol.java.JavaSession;
import net.novatech.jbprotocol.java.JavaSessionData;
import net.novatech.jbprotocol.java.data.JavaPong;
import net.novatech.jbprotocol.listener.*;
import net.novatech.jbprotocol.packet.AbstractPacket;
import net.novatech.jbprotocol.util.MessageConsumer;
import net.novatech.jbserver.event.player.PlayerLoginEvent;
import net.novatech.jbserver.network.INetworkManager;
import net.novatech.jbserver.network.Network;
import net.novatech.jbserver.network.protocol.ProtocolInfo;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.player.java.JavaPlayer;
import net.novatech.jbserver.player.java.JavaPlayerInfo;
import net.novatech.jbserver.server.Server;

public class JavaNetworkManager implements INetworkManager{

	private Network network = null;
	private String ip = "0.0.0.0";
	private int port = 25525;
	public static ExecutorService pool = Executors.newCachedThreadPool();
	
	public JavaNetworkManager(Network network, int port) {
		this.network = network;
		this.ip = network.getServer().getServerSettings().getAddress();
		this.port = port;
	}
	
	@Override
	public void start() {
		pool.execute(() -> {
			ProtocolServer server = new ProtocolServer(this.ip, this.port, GameEdition.JAVA);
			server.setMaxConnections(getNetwork().getServer().getFactoryManager().getPlayerFactory().getMaximalPlayerCount());
			server.setServerListener(new ServerListener() {

				@Override
				public void sessionConnected(GameSession session) {
					JavaSession java = (JavaSession) session;
					java.requireAuthentication(network.getServer().getServerSettings().isOnlineModeEnabled());
					java.setLoginListener(new LoginServerListener() {
						@Override
						public void loginCompleted(SessionData dat) {
							JavaSessionData data = (JavaSessionData)dat;
							JBJavaSession jb = new JBJavaSession(java);
							JavaPlayerInfo info = new JavaPlayerInfo(
									data.getUsername(),
									data.getAddress().getAddress().toString(),
									data.getAddress().getPort(),
									data.getUuid(),
									data.toGameProfile());
							Player player = new JavaPlayer(jb, info);
							Server.getInstance().getFactoryManager().getPlayerFactory().addPlayer(player);
							
							PlayerLoginEvent log = new PlayerLoginEvent(player, info);
							log.call();
						}
						
					});
					java.setGameListener(new GameListener() {
						@Override
						public void receivePacket(AbstractPacket packet) {
							Player player = Server.getInstance().getFactoryManager().getPlayerFactory().searchPlayerBySession(session);
							//player.getSession().handleServerPacket(JavaRetranslatorSector.translateFrom((JavaPacket)packet);
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
					JavaPong p = (JavaPong)pong;
					p.gameVersion = ProtocolInfo.JAVA_VERSION;
					p.protocolVersion = ProtocolInfo.JAVA_PROTOCOL;
					p.motd = Server.getInstance().getServerSettings().getMotd();
					p.description = Server.getInstance().getServerSettings().getMotdUnderline();
					p.maxPlayers = Server.getInstance().getServerSettings().getMaxPlayerCount();
					p.onlinePlayers = Server.getInstance().getFactoryManager().getPlayerFactory().getPlayerCount();
					p.onlinePlayerList = Server.getInstance().getFactoryManager().getPlayerFactory().convertToPongData();
				}
				
			});
			server.bind(new MessageConsumer() {

				@Override
				public void success() {
					getNetwork().getServer().getLogger().info("Started JAVA server on port " + port);
				}

				@Override
				public void failed(Throwable t) {
					getNetwork().getServer().getLogger().error("Failed to start JAVA server: " + t);
				}
				
			});
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
