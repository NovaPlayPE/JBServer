package net.novatech.jbserver.network.bedrock;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.novatech.jbserver.network.INetworkManager;
import net.novatech.jbserver.network.Network;
import net.novatech.jbserver.player.bedrock.BedrockPlayer;
import net.novatech.jbserver.player.bedrock.BedrockPlayerInfo;
import net.novatech.jbserver.server.Server;
import net.novatech.protocol.GameSession;
import net.novatech.protocol.GameVersion;
import net.novatech.protocol.LoginListener;
import net.novatech.protocol.ProtocolServer;
import net.novatech.protocol.ServerListener;
import net.novatech.protocol.util.SessionData;

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
			
			ProtocolServer protocol = new ProtocolServer(new InetSocketAddress(this.ip, this.port), GameVersion.BEDROCK);
			protocol.setMaxConnections(getNetwork().getServer().getServerSettings().getMaxPlayerCount());
			protocol.setServerListener(new ServerListener() {

				@Override
				public void sessionConnected(GameSession session) {
					net.novatech.protocol.bedrock.BedrockSession bedrock = (net.novatech.protocol.bedrock.BedrockSession)session;
					bedrock.setLoginListener(new LoginListener() {
						@Override
						public void loginCompleted(SessionData data) {
							BedrockPlayerInfo info = new BedrockPlayerInfo(data.getUsername(), 
									data.getAddress().getAddress().toString(),
									data.getAddress().getPort(),
									data.getUuid());
							Server.getInstance().getFactoryManager().getPlayerFactory().addPlayer(new BedrockPlayer(new JBBedrockSession(bedrock), info));
						}
						
					});
					bedrock.setGameListener(new BedrockHandler());
				}

				@Override
				public void sessionDisconnected(GameSession session, String cause) {
					// TODO Auto-generated method stub
					
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

