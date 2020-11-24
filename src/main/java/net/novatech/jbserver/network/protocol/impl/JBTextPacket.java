package net.novatech.jbserver.network.protocol.impl;

import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.JBPacketIdentifier;

public class JBTextPacket extends JBPacket{

	@Override
	public JBPacketIdentifier getIdentifier() {
		return JBPacketIdentifier.TEXT_PACKET;
	}
	
	public String content = "";
	
	public boolean isServerBound() {
		return false;
	}
	
	public boolean isClientBound() {
		return true;
	}

}
