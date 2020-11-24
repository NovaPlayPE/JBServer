package net.novatech.jbserver.world;

import lombok.Getter;
import lombok.Setter;
import net.novatech.jbserver.material.MaterialBlock;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.world.chunk.ChunkManager;
import net.novatech.jbserver.world.provider.BaseWorldProvider;

public class World {
	
	@Getter
	private Server server = null;
	
	@Getter
	private String name = null;
	
	@Getter
	private BaseWorldProvider worldProvider = null;
	
	@Getter
	private ChunkManager chunkManager = null;
	
	public World(Server server, String worldId, BaseWorldProvider provider) {
		this.server = server;
		
		this.name = worldId;
		this.worldProvider = provider; 
		
		this.chunkManager = new ChunkManager(this, provider);
	}
	
	public void init() {
		
	}
	
	public void unload() {
		
	}
	
	public void save() {
		
	}
	
	
	
}