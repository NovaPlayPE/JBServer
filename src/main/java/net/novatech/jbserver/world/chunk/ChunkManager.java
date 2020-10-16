package net.novatech.jbserver.world.chunk;

import net.novatech.jbserver.world.World;
import net.novatech.jbserver.world.provider.BaseWorldProvider;

public class ChunkManager {
	
	private World world = null;
	private BaseWorldProvider provider = null;
	
	public ChunkManager(World world, BaseWorldProvider provider) {
		this.world = world;
		this.provider = provider;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public BaseWorldProvider getProvider() {
		return this.provider;
	}
	
	public void setChunk(int chunkX, int chunkZ, Chunk chunk) {
		
	}
	
}