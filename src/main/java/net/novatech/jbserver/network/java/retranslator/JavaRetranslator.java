package net.novatech.jbserver.network.java.retranslator;

import com.github.steveice10.packetlib.packet.Packet;

import net.novatech.jbserver.network.protocol.JBPacket;

public abstract class JavaRetranslator {
	
	public abstract JBPacket translateFrom(Packet pk);
	public abstract Packet translateTo(JBPacket pk);

}
