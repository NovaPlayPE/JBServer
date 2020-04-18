package net.novaplay.jbserver.network.java.retranslator.impl;

import com.github.steveice10.mc.protocol.packet.login.client.LoginStartPacket;
import com.github.steveice10.packetlib.packet.Packet;

import net.novaplay.jbserver.network.java.retranslator.JavaRetranslator;
import net.novaplay.jbserver.network.protocol.JBPacket;

public class LoginRetranslator extends JavaRetranslator {

	@Override
	public JBPacket translateFrom(Packet pk) {
		LoginStartPacket p = (LoginStartPacket)pk;
		return null;
	}

	@Override
	public Packet translateTo(JBPacket pk) {
		// TODO Auto-generated method stub
		return null;
	}

}
