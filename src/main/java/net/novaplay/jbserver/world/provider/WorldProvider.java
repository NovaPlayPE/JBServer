package net.novaplay.jbserver.world.provider;

import net.novaplay.jbserver.world.WorldData;
import net.novaplay.jbserver.world.chunk.Chunk;

public interface WorldProvider {
	
	WorldData loadWorldData();
	void saveWorldData(WorldData worldData);
	
	Chunk loadChunk(int x, int z);
	void saveChunk(Chunk chunk);
	int getChunkCount();
	
}