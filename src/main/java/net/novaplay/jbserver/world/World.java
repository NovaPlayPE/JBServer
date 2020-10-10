package net.novaplay.jbserver.world;

import net.novaplay.jbserver.material.MaterialBlock;
import net.novaplay.jbserver.server.Server;
import net.novaplay.jbserver.world.provider.WorldProvider;

import lombok.Getter;
import lombok.Setter;

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