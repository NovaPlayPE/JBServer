package net.novaplay.jbserver.network.java;

import com.github.steveice10.packetlib.Session;

import lombok.Getter;
import net.novaplay.jbserver.network.NetworkSession;
import net.novaplay.jbserver.network.protocol.JBPacket;

public class JavaSession implements NetworkSession {
	
	public JavaSession(Session session) {
		this.serverSession = session;
	}
	
	@Getter
	private Session serverSession = null;
	
	@Override
	public void sendPacket(JBPacket packet) {
		// TODO Auto-generated method stub
		
	}

}
