package net.novatech.jbserver.network.java;

import lombok.Getter;
import net.novatech.jbprotocol.java.JavaSession;
import net.novatech.jbprotocol.java.packets.JavaPacket;
import net.novatech.jbprotocol.packet.AbstractPacket;
import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.java.retranslator.JavaRetranslatorSector;
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
		this.serverSession.sendPacket(JavaRetranslatorSector.translateTo(packet));
	}

	@Override
	public void handleServerPacket(AbstractPacket packet) {
		if(!(packet instanceof JavaPacket)) {
			return;
		}
	}

}
