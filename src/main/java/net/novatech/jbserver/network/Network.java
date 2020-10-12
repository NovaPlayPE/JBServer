package net.novatech.jbserver.network;

import java.util.HashMap;

import lombok.Getter;
import net.novatech.jbserver.network.bedrock.BedrockNetworkManager;
import net.novatech.jbserver.network.java.JavaNetworkManager;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.JBPacketIdentifier;
import net.novatech.jbserver.network.protocol.JBPacketPairInfo;
import net.novatech.jbserver.server.Server;

public class Network {
	
	@Getter
	public int javaPort = 25565;
	@Getter
	public int bedrockPort = 25565;
	private Server server = null;
	public HashMap<JBPacketIdentifier, JBPacketPairInfo> packetList = new HashMap<JBPacketIdentifier, JBPacketPairInfo>();
	
	private INetworkManager bedrockManager = null;
	private INetworkManager javaManager = null;
	
	public Network(Server server, int port) {
		this.server = server;
		this.javaPort = this.bedrockPort = port;
		startup();
	}
	public Network(Server server, int port1, int port2) {
		this.server = server;
		this.javaPort = port1;
		this.bedrockPort = port2;
		startup();
	}
	
	public void startup() {
		(bedrockManager = new BedrockNetworkManager(this,getBedrockPort())).start();
		(javaManager = new JavaNetworkManager(this,getJavaPort())).start();
	}
	
	public void endup() {
		bedrockManager.stop();
		javaManager.stop();
	}
	
	public Server getServer() {
		return this.server;
	}
	
	
	public void registerPacket(JBPacketIdentifier id, JBPacketPairInfo info) {
		this.packetList.put(id,info);
	}
	
	public void registerPackets() {
		
	}
	
	public void registerBedrockRetranslators() {
		
	}
	
	public void registerJavaRetranslators() {
		
	}

}
