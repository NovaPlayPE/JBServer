package net.novatech.jbserver.network.java.retranslator;

import net.novatech.jbprotocol.java.packets.JavaPacket;
import net.novatech.jbserver.network.protocol.JBPacket;

public abstract class JavaRetranslator {
	
	public abstract JBPacket translateFrom(JavaPacket pk);
	public abstract JavaPacket translateTo(JBPacket pk);

}
