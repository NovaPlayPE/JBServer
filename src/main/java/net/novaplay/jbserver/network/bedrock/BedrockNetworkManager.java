package net.novaplay.jbserver.network.bedrock;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.nukkitx.protocol.bedrock.BedrockServer;

import java.util.*;

import net.novaplay.jbserver.network.INetworkManager;
import net.novaplay.jbserver.network.Network;
import net.novaplay.jbserver.server.Server;

public class BedrockNetworkManager implements INetworkManager{

	private Network network = null;
	private String ip = "0.0.0.0";
	private int port = 19132;
	public static ExecutorService pool = Executors.newCachedThreadPool();
	
	public BedrockNetworkManager(Network network, int port) {
		this.network = network;
		this.ip = network.getServer().getAddress();
		this.port = port;
	}
	
	@Override
	public void start() {
		pool.execute(() -> {
			BedrockServer server = new BedrockServer(new InetSocketAddress(this.ip,this.port));
			server.setHandler(new BedrockHandler(this));
			server.bind().whenComplete((hm,throwable) -> {
				if(throwable != null) {
					getNetwork().getServer().getLogger().error("Failed to start bedrock server: ");
					throwable.printStackTrace();
				} else {
					getNetwork().getServer().getLogger().info("Started bedrock server on port " + this.port);
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

