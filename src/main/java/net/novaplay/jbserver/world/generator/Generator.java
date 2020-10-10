package net.novaplay.jbserver.world.generator;

public interface Generator {
	
	void generateChunk(int chunkX, int chunkZ);
	void populateChunk(int chunkX, int chunkZ);
	
}