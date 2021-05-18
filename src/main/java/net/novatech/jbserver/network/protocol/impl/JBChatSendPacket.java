package net.novatech.jbserver.network.protocol.impl;

import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.JBPacketIdentifier;
import net.novatech.jbserver.player.PlayerInfo;

public class JBChatSendPacket extends JBPacket{

	@Override
	public JBPacketIdentifier getIdentifier() {
		return JBPacketIdentifier.SEND_CHAT_PACKET;
	}
	
	public String content = "";
	public PlayerInfo playerInfo;
	
	public boolean isServerBound() {
		return false;
	}
	
	public boolean isClientBound() {
		return true;
	}

}
