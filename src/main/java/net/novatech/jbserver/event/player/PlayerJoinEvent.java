package net.novatech.jbserver.event.player;

import lombok.Getter;
import lombok.Setter;
import net.novatech.jbserver.event.Cancellable;
import net.novatech.jbserver.player.Player;

public class PlayerJoinEvent extends PlayerEvent implements Cancellable {

	@Getter
	@Setter
	private String joinMessage;
	
	public PlayerJoinEvent(Player player, String joinMessage) {
		super(player);
		this.joinMessage = joinMessage;
	}

}
