package net.novatech.jbserver.player.bedrock;

import net.novatech.jbserver.entity.human.EntityHumanoid;
import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.bedrock.JBBedrockSession;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.player.PlayerInfo;

public class BedrockPlayer extends Player {
	
	private NetworkSession session = null;
	private PlayerInfo playerInfo = null;
	
	public BedrockPlayer(JBBedrockSession session,PlayerInfo info) {
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
