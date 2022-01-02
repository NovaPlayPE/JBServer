package net.novatech.jbserver.event.player;

import net.novatech.jbserver.event.Cancellable;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.world.Location;

import lombok.Getter;
import lombok.Setter;

public class PlayerMoveEvent extends PlayerEvent implements Cancellable{
	
	@Getter
	@Setter
	private Location from;
	
	@Getter
	@Setter
	private Location to;
	
	public PlayerMoveEvent(Player player, Location from, Location to) {
		super(player);
		this.from = from;
		this.to = to;
	}

}
