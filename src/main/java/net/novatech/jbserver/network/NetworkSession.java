package net.novatech.jbserver.network;

import net.novatech.jbprotocol.GameSession;
import net.novatech.jbserver.network.protocol.JBPacket;

public interface NetworkSession {
	
	GameSession getServerSession();
	void sendPacket(JBPacket packet);

}
