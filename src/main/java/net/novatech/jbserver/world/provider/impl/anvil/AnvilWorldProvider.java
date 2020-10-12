package net.novatech.jbserver.world.provider.impl.anvil;

import java.io.File;

import net.novatech.jbserver.world.WorldData;
import net.novatech.jbserver.world.chunk.Chunk;
import net.novatech.jbserver.world.provider.impl.BaseWorldProvider;

public class AnvilWorldProvider extends BaseWorldProvider{
	
	public AnvilWorldProvider(String path) {
		super(path);
	}

	@Override
	public boolean isValid() {
		if(new File(getPath() + "/level.dat").exists() && new File(getPath() + "/region/").isDirectory()) {
			for(File file : new File(getPath() + "/region/").listFiles()) {
				if(file.getName().endsWith(".mca")) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public WorldData load() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(WorldData dat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Chunk readChunk(int x, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeChunk(Chunk chunk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getChunkCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
