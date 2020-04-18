package net.novaplay.jbserver.network.protocol.impl;

import net.novaplay.jbserver.network.protocol.JBPacket;
import net.novaplay.jbserver.network.protocol.JBPacketIdentifier;

public class JBLoginPacket extends JBPacket{

	@Override
	public JBPacketIdentifier getIdentifier() {
		return JBPacketIdentifier.LOGIN;
	}

}
