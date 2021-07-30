package net.novatech.jbserver.world.chunk;

import lombok.Getter;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.world.World;
import net.novatech.jbserver.world.provider.BaseWorldProvider;

public class ChunkManager {
	
	private World world = null;
	private BaseWorldProvider provider = null;
	
	@Getter
	private ChunkCache chunkCache = null;
	
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
	
	public Chunk getChunk(int chunkX, int chunkZ) {
		return getChunk(new ChunkVector(chunkX, chunkZ));
	}
	
	public Chunk getChunk(ChunkVector coords) {
		if(Server.getInstance().getServerSettings().useInMemoryWorlds()) {
			return this.chunkCache.getChunk(coords);
		}
		return this.provider.readChunk(coords);
	}
	
	public void setChunk(int chunkX, int chunkZ, Chunk chunk) {
		
	}
	
}