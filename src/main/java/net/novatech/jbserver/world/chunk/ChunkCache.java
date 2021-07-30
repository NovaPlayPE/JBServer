package net.novatech.jbserver.world.chunk;

import java.util.HashMap;
import java.util.Map;

public class ChunkCache {
	
	public Map<ChunkVector, Chunk> cachedChunks = new HashMap<ChunkVector, Chunk>();
	
	public ChunkCache(Map<ChunkVector, Chunk> chunks) {
		this.cachedChunks = chunks;
	}
	
	public Chunk getChunk(ChunkVector vector) {
		return cachedChunks.get(vector);
	}
	
	public void addChunk(ChunkVector vector, Chunk chunk) {
		this.cachedChunks.put(vector, chunk);
	}
	
	public void setChunks(Map<ChunkVector, Chunk> chunks) {
		this.cachedChunks = chunks;
	}
	
}