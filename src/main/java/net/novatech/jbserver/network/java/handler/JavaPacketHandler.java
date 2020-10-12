package net.novatech.jbserver.network.java.handler;

import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.event.server.ServerBoundEvent;
import com.github.steveice10.packetlib.event.server.ServerClosedEvent;
import com.github.steveice10.packetlib.event.server.ServerClosingEvent;
import com.github.steveice10.packetlib.event.server.ServerListener;
import com.github.steveice10.packetlib.event.server.SessionAddedEvent;
import com.github.steveice10.packetlib.event.server.SessionRemovedEvent;
import com.github.steveice10.packetlib.event.session.ConnectedEvent;
import com.github.steveice10.packetlib.event.session.DisconnectedEvent;
import com.github.steveice10.packetlib.event.session.DisconnectingEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.PacketSendingEvent;
import com.github.steveice10.packetlib.event.session.PacketSentEvent;
import com.github.steveice10.packetlib.event.session.SessionListener;

public class JavaPacketHandler implements ServerListener {

	@Override
	public void serverBound(ServerBoundEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void serverClosing(ServerClosingEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void serverClosed(ServerClosedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionAdded(SessionAddedEvent event) {
		Session session = event.getSession();
		session.addListener(new SessionListener() {

			@Override
			public void packetReceived(PacketReceivedEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void packetSending(PacketSendingEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void packetSent(PacketSentEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void connected(ConnectedEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disconnecting(DisconnectingEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disconnected(DisconnectedEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	@Override
	public void sessionRemoved(SessionRemovedEvent event) {
		// TODO Auto-generated method stub

	}

}
