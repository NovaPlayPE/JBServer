package net.novatech.jbserver.world.generator.populator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.novatech.jbserver.material.MaterialBlock;
import net.novatech.jbserver.world.biomes.Biome;
import net.novatech.jbserver.world.chunk.Chunk;
import net.novatech.jbserver.world.chunk.ChunkManager;
import net.novatech.jbserver.world.generator.Populator;
import net.novatech.jbserver.world.tree.NormalTree;
import net.novatech.jbserver.world.tree.Tree;
import net.novatech.library.math.Vector3i;

public class TreePopulator implements Populator{
	
	private Biome biome;
	protected List<Tree> trees;

	public TreePopulator(Biome biome) {
		this.biome = biome;
		this.trees = new ArrayList<Tree>();
	}
	
	@Override
	public void populate(Random rand, int blockX, int blockZ, ChunkManager mgr) {
		int max = 256; // TODO
		int min = 0;
		
		Chunk chunk = mgr.getChunk(blockX >> 4, blockZ >> 4);
		MaterialBlock block = chunk.getBlockAt(blockX & 0xF, min + 1, blockZ & 0xF);
		for(int y = max; y >= min; y--) {
			this.placeTree(rand, this.biome, blockX, y, blockZ, chunk);
		}
	}
	
	public void placeTree(Random rand, Biome biome, int x, int y, int z, Chunk chunk) {
		Tree tree = this.findTreeByBiome(biome);
		tree.place(rand, new Vector3i(x, y, z), chunk);
		trees.add(tree);
	}
	
	private Tree findTreeByBiome(Biome biome) {
		return new NormalTree();
	}
	
}