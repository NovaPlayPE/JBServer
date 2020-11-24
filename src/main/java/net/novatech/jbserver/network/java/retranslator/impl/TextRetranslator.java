package net.novatech.jbserver.network.java.retranslator.impl;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerChatPacket;
import com.github.steveice10.packetlib.packet.Packet;

import net.novatech.jbserver.network.java.retranslator.JavaRetranslator;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.impl.JBTextPacket;

public class TextRetranslator extends JavaRetranslator {

	@Override
	public JBPacket translateFrom(Packet pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packet translateTo(JBPacket pk) {
		JBTextPacket p = (JBTextPacket)pk;
		
		ServerChatPacket packet = new ServerChatPacket(p.content);
		return packet;
	}

}
