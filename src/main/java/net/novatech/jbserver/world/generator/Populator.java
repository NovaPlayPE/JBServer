package net.novatech.jbserver.world.generator;

import java.util.Random;

import net.novatech.jbserver.world.chunk.ChunkManager;

public interface Populator {
	
	void populate(Random rand, int blockX, int blockZ, ChunkManager mgr);
	
}