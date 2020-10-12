package net.novatech.jbserver.world.provider;

import net.novatech.jbserver.world.WorldData;
import net.novatech.jbserver.world.chunk.Chunk;

public interface WorldProvider {
	
	WorldData loadWorldData();
	void saveWorldData(WorldData worldData);
	
	Chunk loadChunk(int x, int z);
	void saveChunk(Chunk chunk);
	int getChunkCount();
	
}