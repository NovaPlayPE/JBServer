package net.novaplay.jbserver.world.provider.impl;

import net.novaplay.jbserver.world.WorldData;
import net.novaplay.jbserver.world.chunk.Chunk;
import net.novaplay.jbserver.world.provider.WorldProvider;

public abstract class BaseWorldProvider implements WorldProvider {
	
	protected String path;
	
	public BaseWorldProvider(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public abstract boolean isValid();
	
	public abstract WorldData load();
	public abstract void save(WorldData dat);
	
	@Override
	public WorldData loadWorldData() {
		return load();
	}

	@Override
	public void saveWorldData(WorldData worldData) {
		save(worldData);
	}

	public abstract Chunk readChunk(int x, int z);
	public abstract void writeChunk(Chunk chunk);
	
	@Override
	public Chunk loadChunk(int x, int z) {
		return readChunk(x,z);
	}

	@Override
	public void saveChunk(Chunk chunk) {
		writeChunk(chunk);
	}

	public abstract int getChunkCount();

}
