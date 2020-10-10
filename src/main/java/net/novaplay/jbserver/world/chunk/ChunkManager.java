package net.novaplay.jbserver.world.chunk;

import net.novaplay.jbserver.world.World;
import net.novaplay.jbserver.world.provider.WorldProvider;

public class ChunkManager {
	
	private World world = null;
	private WorldProvider provider = null;
	
	public ChunkManager(World world, WorldProvider provider) {
		this.world = world;
		this.provider = provider;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public WorldProvider getProvider() {
		return this.provider;
	}
	
	public void setChunk(int chunkX, int chunkZ, Chunk chunk) {
		
	}
	
}