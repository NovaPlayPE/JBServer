package net.novaplay.jbserver.network.bedrock.retranslator;

import com.nukkitx.protocol.bedrock.BedrockPacket;

import net.novaplay.jbserver.network.protocol.JBPacket;

public abstract class BedrockRetranslator {
	
	public abstract JBPacket translateFrom(BedrockPacket pk);
	public abstract BedrockPacket translateTo(JBPacket pk);
	
}
