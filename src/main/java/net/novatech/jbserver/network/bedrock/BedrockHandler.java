package net.novatech.jbserver.network.bedrock;

import java.net.InetSocketAddress;

import com.nukkitx.protocol.bedrock.BedrockPong;
import com.nukkitx.protocol.bedrock.BedrockServerEventHandler;
import com.nukkitx.protocol.bedrock.BedrockServerSession;

import net.novatech.jbserver.network.bedrock.handler.BedrockLoginHandler;
import net.novatech.jbserver.server.Server;

public class BedrockHandler implements BedrockServerEventHandler {
	
	private BedrockNetworkManager mgr = null;
	
	public BedrockNetworkManager getManager() { return this.mgr; }
	
	public BedrockHandler(BedrockNetworkManager mgr) {
		this.mgr = mgr;
	}
	
	@Override
	public boolean onConnectionRequest(InetSocketAddress address) {
		return false;
	}

	@Override
	public BedrockPong onQuery(InetSocketAddress address) {
		BedrockPong pong = new BedrockPong();
		pong.setEdition("MCPE");
		pong.setMotd(Server.getInstance().getNetwork().getServer().getServerSettings().getMotd());
		pong.setSubMotd(Server.getInstance().getNetwork().getServer().getServerSettings().getMotdUnderline());
		pong.setIpv4Port(getManager().getPort());
		pong.setIpv6Port(getManager().getPort());
		pong.setNintendoLimited(false);
		pong.setPlayerCount(Server.getInstance().getPlayerManager().getPlayerCount());
		pong.setMaximumPlayerCount(Server.getInstance().getPlayerManager().getMaximalPlayerCount());
		return pong;
	}

	@Override
	public void onSessionCreation(BedrockServerSession session) {
		BedrockSession s = new BedrockSession(session);
		session.setLogging(true);
		session.setPacketHandler(new BedrockLoginHandler(s));
		session.addDisconnectHandler(reason -> {
			
		});
	}

}
