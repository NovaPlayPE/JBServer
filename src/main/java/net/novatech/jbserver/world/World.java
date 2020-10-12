package net.novatech.jbserver.world;

import lombok.Getter;
import lombok.Setter;
import net.novatech.jbserver.material.MaterialBlock;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.world.provider.WorldProvider;

public class World {
	
	@Getter
	private Server server = null;
	
	@Getter
	private String name = null;
	
	@Getter
	private WorldProvider worldProvider = null;
	
	public World(Server server, String worldId, WorldProvider provider) {
		this.server = server;
		
		this.name = worldId;
		this.worldProvider = provider; 
	}
	
	public void init() {
		
	}
	
	public void unload() {
		
	}
	
	public void save() {
		
	}
	
	
	
}