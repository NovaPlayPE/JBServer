package net.novatech.jbserver.player.bedrock;

import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.bedrock.BedrockSession;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.player.PlayerInfo;

public class BedrockPlayer implements Player {
	
	private NetworkSession session = null;
	private PlayerInfo playerInfo = null;
	public BedrockPlayer(BedrockSession session,PlayerInfo info) {
		this.session = session;
		this.playerInfo = info;
	}
	
	@Override
	public NetworkSession getSession() {
		return this.session;
	}

	@Override
	public PlayerInfo getPlayerInfo() {
		return this.playerInfo;
	}

	@Override
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOp(boolean value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOp() {
		// TODO Auto-generated method stub
		return false;
	}

}
