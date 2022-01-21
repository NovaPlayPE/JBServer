package net.novatech.jbserver.world.chunk;

import java.util.ArrayList;
import java.util.List;

public class ChunkCache {
	
	public List<Chunk> cachedChunks = new ArrayList<Chunk>();
	
	public ChunkCache(List<Chunk> chunks) {
		this.cachedChunks = chunks;
	}
	
	public Chunk getChunk(ChunkVector vector) {
		for(Chunk chunk : cachedChunks) {
			if(chunk.getVector().equals(vector)) {
				return chunk;
			}
		}
		return null;
	}
	
	public void addChunk(Chunk chunk) {
		this.cachedChunks.add(chunk);
	}
	
	public void setChunks(List<Chunk> chunks) {
		this.cachedChunks = chunks;
	}
	
}