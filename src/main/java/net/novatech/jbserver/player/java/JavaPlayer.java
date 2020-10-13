package net.novatech.jbserver.player.java;

import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.java.JavaSession;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.player.PlayerInfo;

public class JavaPlayer implements Player {

	private NetworkSession session = null;
	private PlayerInfo playerInfo = null;
	
	public JavaPlayer(JavaSession session, PlayerInfo info) {
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
