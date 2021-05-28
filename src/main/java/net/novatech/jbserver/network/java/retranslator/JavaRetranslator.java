package net.novatech.jbserver.network.java.retranslator;

import net.novatech.jbprotocol.java.packets.JavaPacket;
import net.novatech.jbserver.network.protocol.JBPacket;

public abstract class JavaRetranslator<T extends JavaPacket> {
	
	public abstract T translate(JBPacket pk);

}
