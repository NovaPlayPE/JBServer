package net.novatech.jbserver.network.java;

import lombok.Getter;
import net.novatech.jbprotocol.java.JavaSession;
import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.protocol.JBPacket;

public class JBJavaSession implements NetworkSession {
	
	public JBJavaSession(JavaSession session) {
		this.serverSession = session;
	}
	
	@Getter
	private JavaSession serverSession = null;
	
	@Override
	public void sendPacket(JBPacket packet) {
		// TODO Auto-generated method stub
		
	}

}
