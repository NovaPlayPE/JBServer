package net.novatech.jbserver.network.bedrock.retranslator.impl;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.TextPacket;

import net.novatech.jbserver.network.bedrock.retranslator.BedrockRetranslator;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.impl.JBTextPacket;

public class TextRetranslator extends BedrockRetranslator{

	@Override
	public JBPacket translateFrom(BedrockPacket pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BedrockPacket translateTo(JBPacket pk) {
		JBTextPacket p = (JBTextPacket)pk;
		
		TextPacket packet = new TextPacket();
		packet.setMessage(p.content);
		return packet;
	}
	
	
	
}