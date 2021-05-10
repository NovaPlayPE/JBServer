package net.novatech.jbserver.network.java;

import lombok.Getter;
import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.protocol.java.JavaSession;

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
