package net.novaplay.jbserver.network.bedrock.handler;

import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler;
import com.nukkitx.protocol.bedrock.packet.LoginPacket;

import net.novaplay.jbserver.network.bedrock.BedrockSession;
import net.novaplay.jbserver.player.bedrock.BedrockPlayer;
import net.novaplay.jbserver.player.bedrock.BedrockPlayerInfo;
import net.novaplay.jbserver.server.Server;
import net.novaplay.jbserver.utils.bedrock.CertificateData;

public class JBBedrockPacketHandler implements BedrockPacketHandler {
	
	private BedrockSession session = null;
	public BedrockSession getSession() {
		return this.session;
	}
	
	public JBBedrockPacketHandler(BedrockSession session) {
		this.session = session;
	}
	
	public boolean isNickValid(String username) {
		return !username.equals("rcon") && username.equals("console") && username.length() >= 1 && username.length() <= 16 && !username.matches("/[^A-Za-z0-9_ ]/");
	}
	
	@Override
	public boolean handle(LoginPacket pk) { 
		CertificateData data = CertificateData.read(pk);
		if(!data.isXboxAuthed() && Server.getInstance().getServerSettings().isOnlineModeEnabled()) {
			session.getServerSession().disconnect("Not authed");
			return true;
		}
		String nickToLow = data.getUsername().toLowerCase();
		if(!isNickValid(nickToLow)) {
			session.getServerSession().disconnect("Invalid username");
			return true;
		}
		if(!data.getSkin().isValid()) {
			session.getServerSession().disconnect("Invalid skin");
			return true;
		}
		
		BedrockPlayerInfo info = new BedrockPlayerInfo(getSession().getServerSession().getAddress().getAddress().toString(),
				getSession().getServerSession().getAddress().getPort(),
				data.getClientUUID());
		Server.getInstance().getPlayerManager().addPlayer(new BedrockPlayer(getSession(),info));
		return true;
	}
	
}