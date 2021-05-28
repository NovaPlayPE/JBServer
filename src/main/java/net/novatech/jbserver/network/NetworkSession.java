package net.novatech.jbserver.network;

import net.novatech.jbprotocol.GameSession;
import net.novatech.jbprotocol.packet.AbstractPacket;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.player.Player;

public interface NetworkSession {
	
	void setPlayer(Player player);
	GameSession getServerSession();
	void sendPacket(JBPacket packet);
	void handleServerPacket(AbstractPacket packet);

}
