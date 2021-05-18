package net.novatech.jbserver.network.java;

import lombok.Getter;
import net.novatech.jbprotocol.java.JavaSession;
import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.impl.*;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.player.java.JavaPlayer;

public class JBJavaSession implements NetworkSession {
	
	public JBJavaSession(JavaSession session) {
		this.serverSession = session;
	}
	
	@Getter
	private JavaPlayer player = null;
	
	public void setPlayer(Player player) {
		this.player = (JavaPlayer) player;
	}
	
	@Getter
	private JavaSession serverSession = null;
	
	@Override
	public void sendPacket(JBPacket packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleServerPacket(JBPacket packet) {
		switch(packet.getIdentifier()) {
		case TEXT_PACKET:
			JBChatSendPacket pk = (JBChatSendPacket)packet;
			getPlayer().getServer().getBroadcaster().broadcastMessage(pk.message);
			break;
		}
	}

}
