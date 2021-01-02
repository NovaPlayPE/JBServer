package net.novatech.jbserver.event.world;

import net.novatech.jbserver.world.World;

public class WorldLoadEvent extends WorldEvent {

	private long time;
	
	public WorldLoadEvent(World world, long time) {
		super(world);
		this.time = time;
	}
	
	public long getLoadTime() {
		return this.time;
	}

}
