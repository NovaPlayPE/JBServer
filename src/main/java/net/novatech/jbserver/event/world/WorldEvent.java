package net.novatech.jbserver.event.world;

import net.novatech.jbserver.event.Event;
import net.novatech.jbserver.world.World;

public abstract class WorldEvent extends Event{
	
	private World world;
	
	public WorldEvent(World world) {
		this.world = world;
	}
	
	public World getWorld() {
		return this.world;
	}
	
}