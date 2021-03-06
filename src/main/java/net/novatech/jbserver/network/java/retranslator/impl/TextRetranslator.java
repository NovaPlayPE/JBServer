package net.novatech.jbserver.network.java.retranslator.impl;

import net.novatech.jbprotocol.java.packets.JavaPacket;
import net.novatech.jbprotocol.java.packets.play.clientbound.ClientChatPacket;
import net.novatech.jbserver.network.java.retranslator.JavaRetranslator;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.impl.JBChatSendPacket;
import net.novatech.jbserver.player.java.JavaPlayerInfo;
import net.novatech.jbserver.utils.Color;

public class TextRetranslator extends JavaRetranslator<ClientChatPacket> {

	@Override
	public ClientChatPacket translate(JBPacket packet) {
		JBChatSendPacket pk = (JBChatSendPacket)packet;
		JavaPlayerInfo info = (JavaPlayerInfo)pk.playerInfo;
		
		ClientChatPacket text = new ClientChatPacket();
		text.message = pk.content;
		text.type = ClientChatPacket.JavaChatType.CHAT;
		text.sender = info.getUniqueId();
		
		return text;
	}

}
