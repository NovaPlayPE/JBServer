package net.novatech.jbserver.world.generator;

import java.util.ArrayList;
import java.util.List;

import net.novatech.jbserver.world.World;
import net.novatech.jbserver.world.chunk.Chunk;

public class NormalGenerator implements Generator {
	
	private World world;
	
	private List<Populator> populators = new ArrayList<Populator>();
	
	@Override
	public void generateChunk(int chunkX, int chunkZ) {
		int baseX = chunkX << 4;
		int baseZ = chunkZ << 4;
		
		Chunk chunk = this.world.getChunkManager().getChunk(baseX, baseZ);
	}

	@Override
	public void populateChunk(int chunkX, int chunkZ) {
		// TODO Auto-generated method stub

	}

}
