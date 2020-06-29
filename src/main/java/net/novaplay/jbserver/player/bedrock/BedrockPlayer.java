package net.novaplay.jbserver.player.bedrock;

import net.novaplay.jbserver.network.NetworkSession;
import net.novaplay.jbserver.network.bedrock.BedrockSession;
import net.novaplay.jbserver.player.Player;
import net.novaplay.jbserver.player.PlayerInfo;

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
