package net.novaplay.jbserver.network.java;

import java.net.InetAddress;
import java.net.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.mc.protocol.data.message.TextMessage;
import com.github.steveice10.mc.protocol.data.status.PlayerInfo;
import com.github.steveice10.mc.protocol.data.status.ServerStatusInfo;
import com.github.steveice10.mc.protocol.data.status.VersionInfo;
import com.github.steveice10.mc.protocol.data.status.handler.ServerInfoBuilder;
import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.protocol.MinecraftConstants;
import com.github.steveice10.packetlib.Server;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.tcp.TcpSessionFactory;

import net.novaplay.jbserver.network.INetworkManager;
import net.novaplay.jbserver.network.Network;
import net.novaplay.jbserver.network.java.handler.JavaLoginHandler;

public class JavaNetworkManager implements INetworkManager{

	private Network network = null;
	private String ip = "0.0.0.0";
	private int port = 25525;
	public static ExecutorService pool = Executors.newCachedThreadPool();
	
	public JavaNetworkManager(Network network, int port) {
		this.network = network;
		this.ip = network.getServer().getAddress();
		this.port = port;
	}
	
	@Override
	public void start() {
		pool.execute(() -> {
			Server server = new Server(ip, port, MinecraftProtocol.class, new TcpSessionFactory(Proxy.NO_PROXY));
			server.setGlobalFlag(MinecraftConstants.VERIFY_USERS_KEY,true);
			server.setGlobalFlag(MinecraftConstants.SERVER_LOGIN_HANDLER_KEY, new JavaLoginHandler(this));
			server.setGlobalFlag(MinecraftConstants.SERVER_INFO_BUILDER_KEY, new ServerInfoBuilder() {
				@Override
				public ServerStatusInfo buildInfo(Session session) {
					ServerStatusInfo info = new ServerStatusInfo(
							new VersionInfo(MinecraftConstants.GAME_VERSION,MinecraftConstants.PROTOCOL_VERSION),
							new PlayerInfo(getNetwork().getServer().getPlayerManager().getMaximalPlayerCount(), getNetwork().getServer().getPlayerManager().getPlayerCount(), new GameProfile[0]),
							new TextMessage(getNetwork().getMotd()),
							null
							);
					return info;
				}
			});
			server.bind();
			getNetwork().getServer().getLogger().info("Started java server on port " + this.port);
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
