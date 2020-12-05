package net.novatech.jbserver.event.player;

import net.novatech.jbserver.event.Cancellable;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.player.PlayerInfo;

public class PlayerLoginEvent extends PlayerEvent implements Cancellable{

	private PlayerInfo playerInfo;
	
	public PlayerLoginEvent(Player player, PlayerInfo info) {
		super(player);
		this.playerInfo = info;
	}
	
	public PlayerInfo getPlayerInfo() {
		return this.playerInfo;
	}

}