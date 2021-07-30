package net.novatech.jbserver.world.provider.impl;

import java.io.File;
import java.io.IOException;

import net.novatech.jbserver.manager.PathManager;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.world.WorldData;
import net.novatech.jbserver.world.chunk.Chunk;
import net.novatech.jbserver.world.chunk.ChunkVector;
import net.novatech.jbserver.world.provider.BaseWorldProvider;
import net.novatech.jbserver.world.provider.WorldProviderManager;
import net.novatech.library.nbt.NBTIO;
import net.novatech.library.nbt.tags.CompoundTag;

public class AnvilWorldProvider extends BaseWorldProvider{
	
	public AnvilWorldProvider(String path) {
		super(path);
	}

	@Override
	public boolean isValid() {
		if(new File(getAbsolutePath() + "/level.dat").exists() && new File(getAbsolutePath() + "/region/").isDirectory()) {
			for(File file : new File(getAbsolutePath() + "/region/").listFiles()) {
				if(file.getName().endsWith(".mca")) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void load() {
		this.worldData = loadWorldData(new File(getAbsolutePath() + "/level.dat"));
		
	}
	
	@Override
	public void create() {
		
	}

	@Override
	public void setWorldData(WorldData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WorldData loadWorldData(File file) {
		if(isValid()) {
			CompoundTag nbt;
			try {
				nbt = NBTIO.read(file);
				return WorldData.readFromNbt(nbt, WorldProviderManager.ANVIL);
			} catch (IOException e) {
				Server.getInstance().getLogger().logException(e);
			}
		}
		return null;
	}

	@Override
	public void saveWorldData(WorldData worldData) {
		// TODO Auto-generated method stub
		
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

	@Override
	public Chunk readChunk(ChunkVector vector) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
