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
		if(new File(getAbsolutePath() + "/level.dat").exists() && new File(getAbsolutePath() + "/db/").isDirectory()) {
			return true;
		}
		return false;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWorldData(WorldData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WorldData loadWorldData(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWorldData(WorldData worldData) {
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