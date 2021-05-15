package net.novatech.jbserver.network.bedrock.retranslator;

import net.novatech.jbprotocol.bedrock.packets.BedrockPacket;
import net.novatech.jbserver.network.protocol.JBPacket;

public abstract class BedrockRetranslator {
	
	public abstract JBPacket translateFrom(BedrockPacket pk);
	public abstract BedrockPacket translateTo(JBPacket pk);
	
}
