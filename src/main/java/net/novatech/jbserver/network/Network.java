package net.novatech.jbserver.network;

import java.util.HashMap;

import lombok.Getter;
import net.novatech.jbserver.network.bedrock.BedrockNetworkManager;
import net.novatech.jbserver.network.java.JavaNetworkManager;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.JBPacketIdentifier;
import net.novatech.jbserver.server.Server;

public class Network {
	
	@Getter
	public int javaPort = 25565;
	@Getter
	public int bedrockPort = 25565;
	private Server server = null;
	
	private boolean javaEnabled;
	private boolean bedrockEnabled;
	
	private INetworkManager bedrockManager = null;
	private INetworkManager javaManager = null;
	
	public Network(Server server, boolean java, boolean bedrock) {
		this(server, java, bedrock, 25565);
	}
	
	public Network(Server server, boolean java, boolean bedrock, int port) {
		this(server, java, bedrock, port, 19132);
	}
	
	public Network(Server server, boolean java, boolean bedrock, int javaPort, int bedrockPort) {
		this.server = server;
		this.javaEnabled = java;
		this.bedrockEnabled = bedrock;
		this.javaPort = javaPort;
		this.bedrockPort = bedrockPort;
		startup();
	}
	
	public void startup() {
		if(this.bedrockEnabled) {
			(bedrockManager = new BedrockNetworkManager(this,getBedrockPort())).start();
		}
		if(this.javaEnabled) {
			(javaManager = new JavaNetworkManager(this,getJavaPort())).start();
		}
	}
	
	public void endup() {
		if(bedrockManager != null) bedrockManager.stop();
		if(javaManager != null) javaManager.stop();
	}
	
	public Server getServer() {
		return this.server;
	}
	
	public void registerPackets() {
		
	}
	
	public void registerBedrockRetranslators() {
		
	}
	
	public void registerJavaRetranslators() {
		
	}

}
