package net.novatech.jbserver.network.bedrock.retranslator;

import net.novatech.jbprotocol.bedrock.packets.BedrockPacket;
import net.novatech.jbserver.network.protocol.JBPacket;

public abstract class BedrockRetranslator<T extends BedrockPacket> {
	
	public abstract T translate(JBPacket pk);
	
}
