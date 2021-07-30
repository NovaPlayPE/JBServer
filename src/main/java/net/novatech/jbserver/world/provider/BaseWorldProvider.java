package net.novatech.jbserver.world.provider;

import java.io.File;

import lombok.Getter;
import net.novatech.jbserver.manager.PathManager;
import net.novatech.jbserver.world.WorldData;
import net.novatech.jbserver.world.chunk.Chunk;
import net.novatech.jbserver.world.chunk.ChunkCache;
import net.novatech.jbserver.world.chunk.ChunkVector;

public abstract class BaseWorldProvider {
	
	protected String path;
	@Getter
	public WorldData worldData;
	@Getter
	public ChunkCache chunkCache;
	
	public BaseWorldProvider(String worldName) {
		this.path = worldName;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public String getAbsolutePath() {
		return PathManager.getWorldPath() + getPath();
	}
	
	public abstract void load();
	public abstract void create();
	
	public abstract boolean isValid();
	
	public abstract void setWorldData(WorldData data);
	public abstract WorldData loadWorldData(File file);
	public abstract void saveWorldData(WorldData worldData);
	
	public abstract Chunk readChunk(ChunkVector vector);
	public abstract void writeChunk(Chunk chunk);

	public abstract int getChunkCount();

}
