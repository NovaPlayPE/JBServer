package net.novaplay.jbserver.network.java;

import com.github.steveice10.mc.protocol.ServerLoginHandler;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.event.server.ServerAdapter;
import com.github.steveice10.packetlib.event.server.SessionAddedEvent;

public class JavaHandler extends ServerAdapter implements ServerLoginHandler{
	
	private JavaNetworkManager mgr = null;
	
	public JavaHandler(JavaNetworkManager mgr) {
		this.mgr = mgr;
	}
	
	public JavaNetworkManager getManager() { return this.mgr; }

	@Override
	public void loggedIn(Session session) {
			
	}
	
	@Override
	public void sessionAdded(SessionAddedEvent e) {
		
	}

}
