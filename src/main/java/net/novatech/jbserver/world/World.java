package net.novatech.jbserver.world;

import lombok.Getter;
import lombok.Setter;
import net.novatech.jbserver.material.MaterialBlock;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.world.chunk.ChunkManager;
import net.novatech.jbserver.world.provider.BaseWorldProvider;
import net.novatech.library.math.Vector3d;

public class World {
	
	@Getter
	private Server server = null;
	
	@Getter
	private String name = null;
	
	@Getter
	private BaseWorldProvider worldProvider = null;
	
	@Getter
	private Vector3d worldSpawn = null;
	
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
	
	public void load() {
		this.worldProvider.load();
		WorldData worldData = this.worldProvider.getWorldData();
	}
	
	public void unload() {
		
	}
	
	public void save() {
		
	}
	
	
	
}