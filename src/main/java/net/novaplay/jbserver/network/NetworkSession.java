package net.novaplay.jbserver.network;

import net.novaplay.jbserver.network.protocol.JBPacket;

public interface NetworkSession {
	
	void sendPacket(JBPacket packet);

}
