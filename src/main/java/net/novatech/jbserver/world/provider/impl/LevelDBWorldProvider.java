package net.novatech.jbserver.world.provider.impl;

import java.io.File;

import net.novatech.jbserver.manager.PathManager;
import net.novatech.jbserver.world.WorldData;
import net.novatech.jbserver.world.chunk.Chunk;
import net.novatech.jbserver.world.provider.BaseWorldProvider;

public class LevelDBWorldProvider extends BaseWorldProvider{
	
	public LevelDBWorldProvider(String path) {
		super(path);
	}

	@Override
	public boolean isValid() {
		if((new File(getAbsolutePath() + "/level.dat").exists() && new File(getAbsolutePath() + "/db/").isDirectory()) || new File(getAbsolutePath() + ".mcworld").exists()) {
			return true;
		}
		return false;
	}

	@Override
	public void load() {
		
		
	}
	
	@Override
	public void create() {
		
	}

	@Override
	public void setWorldData(WorldData data) {
		
		
	}

	@Override
	public WorldData loadWorldData(File file) {
		
		return null;
	}

	@Override
	public void saveWorldData(WorldData worldData) {
		
		
	}

	@Override
	public Chunk readChunk(int x, int z) {
		
		return null;
	}

	@Override
	public void writeChunk(Chunk chunk) {
		
		
	}

	@Override
	public int getChunkCount() {
		
		return 0;
	}
	
}