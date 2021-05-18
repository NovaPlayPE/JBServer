package net.novatech.jbserver.network.bedrock.retranslator.impl;

import net.novatech.jbprotocol.bedrock.packets.BedrockPacket;
import net.novatech.jbprotocol.bedrock.packets.TextPacket;
import net.novatech.jbserver.network.bedrock.retranslator.BedrockRetranslator;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.impl.JBChatSendPacket;
import net.novatech.jbserver.player.bedrock.BedrockPlayerInfo;
import net.novatech.jbserver.utils.Color;

public class TextRetranslator extends BedrockRetranslator{

	@Override
	public JBPacket translateFrom(BedrockPacket pk) {
		return null; // nothing, lol...
	}

	@Override
	public BedrockPacket translateTo(JBPacket packet) {
		JBChatSendPacket pk = (JBChatSendPacket)packet;
		BedrockPlayerInfo info = (BedrockPlayerInfo)pk.playerInfo;
		
		TextPacket text = new TextPacket();
		text.type = TextPacket.BedrockTextType.CHAT;
		text.message = pk.content;
		text.xuid = info.getXuid();
		return text;
	}
	
	
	
}