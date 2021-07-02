package net.novatech.jbserver.world.tree;

import java.util.Random;

import net.novatech.jbserver.material.Material;
import net.novatech.jbserver.world.chunk.Chunk;
import net.novatech.library.math.Vector3i;

public class NormalTree extends Tree {
	
	public NormalTree() {
		//setup biome
	}
	
	@Override
	public void placeWood(Random rand, Vector3i start, int height, Chunk chunk) throws Exception {
		for(int kmen = 0; kmen < height; kmen++) {
			chunk.setBlock(Material.WOOD.toBlock(new Vector3i(start.getX(), start.getY() + kmen, start.getZ())));
		}
	}

	@Override
	public void placeLeaves(Random rand, Vector3i start, int height, Chunk chunk) throws Exception {
		for(int yy = start.getY() + height - 3; yy <= start.getY() + height; yy++) {
			int dy = yy - (height + start.getY());
			int radius = 1 - (dy/2);
			for(int dx = -radius; dx <= radius; dx++) {
				for(int dz = -radius; dz <= radius; dz++) {
					if(Math.abs(dx) != radius || Math.abs(dz) != radius || rand.nextBoolean() && dy != 0) {
						chunk.setBlock(Material.WOOD.toBlock(new Vector3i(start.getX() + dx, yy, start.getZ() + dz)));
					}
				}
			}
		}
	}

}
