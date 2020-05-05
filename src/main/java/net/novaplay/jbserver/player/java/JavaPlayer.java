package net.novaplay.jbserver.player.java;

import net.novaplay.jbserver.network.NetworkSession;
import net.novaplay.jbserver.network.java.JavaSession;
import net.novaplay.jbserver.player.Player;
import net.novaplay.jbserver.player.PlayerInfo;
import net.novaplay.jbserver.player.PlayerManager;

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

}
