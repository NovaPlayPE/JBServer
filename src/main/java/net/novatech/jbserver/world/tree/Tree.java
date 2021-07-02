package net.novatech.jbserver.world.tree;

import java.util.Random;

import net.novatech.jbserver.material.MaterialBlock;
import net.novatech.jbserver.world.biomes.Biome;
import net.novatech.jbserver.world.chunk.Chunk;
import net.novatech.jbserver.world.chunk.ChunkManager;
import net.novatech.library.math.Vector3i;

public abstract class Tree {
	
	public MaterialBlock[] wood;
	public MaterialBlock[] leaves;
	
	protected Biome biome;
	
	public void place(Random rand, Vector3i pos, Chunk chunk) {
		int y = pos.getY();
		if(y < 0 || y >= 256) {
			return;
		}
		int height = 0; //again to do
		
		try {
			this.placeWood(rand, pos, height, chunk);
			this.placeLeaves(rand, pos, height, chunk);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract void placeWood(Random rand, Vector3i start, int height, Chunk chunk) throws Exception;
	public abstract void placeLeaves(Random rand, Vector3i start, int height, Chunk chunk) throws Exception;
	
}