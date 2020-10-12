package net.novatech.jbserver.world.provider.impl.leveldb;

import java.io.File;

import net.novatech.jbserver.world.WorldData;
import net.novatech.jbserver.world.chunk.Chunk;
import net.novatech.jbserver.world.provider.WorldProvider;
import net.novatech.jbserver.world.provider.impl.BaseWorldProvider;

public class LevelDBWorldProvider extends BaseWorldProvider{
	
	public LevelDBWorldProvider(String path) {
		super(path);
	}

	@Override
	public boolean isValid() {
		if(new File(getPath() + "/level.dat").exists() && new File(getPath() + "/db/").isDirectory()) {
			return true;
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