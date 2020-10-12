package net.novatech.jbserver.network.protocol;

import com.github.steveice10.packetlib.packet.Packet;
import com.nukkitx.protocol.bedrock.BedrockPacket;

public class JBPacketPairInfo {
	
	private Class<? extends Packet> javaPacket;
	private Class<? extends BedrockPacket> bedrockPacket;
	
	public JBPacketPairInfo(Class<? extends Packet> p1, Class<? extends BedrockPacket> p2) {
		javaPacket = p1; bedrockPacket = p2;
	}
	
	public Class<? extends Packet> getJavaPacket(){
		return this.javaPacket;
	}
	
	public Class<? extends BedrockPacket> getBedrockPacket(){
		return this.bedrockPacket;
	}

}
