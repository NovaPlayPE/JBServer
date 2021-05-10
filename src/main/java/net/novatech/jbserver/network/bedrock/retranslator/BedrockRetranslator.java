package net.novatech.jbserver.network.bedrock.retranslator;

import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.protocol.bedrock.packets.BedrockPacket;

public abstract class BedrockRetranslator {
	
	public abstract JBPacket translateFrom(BedrockPacket pk);
	public abstract BedrockPacket translateTo(JBPacket pk);
	
}
