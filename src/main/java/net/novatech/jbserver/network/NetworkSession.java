package net.novatech.jbserver.network;

import net.novatech.jbserver.network.protocol.JBPacket;

public interface NetworkSession {
	
	void sendPacket(JBPacket packet);

}
