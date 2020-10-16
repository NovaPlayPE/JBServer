package net.novatech.jbserver.world.provider.impl;

import java.io.File;

import lombok.Getter;
import net.novatech.jbserver.manager.PathManager;
import net.novatech.jbserver.world.WorldData;
import net.novatech.jbserver.world.chunk.Chunk;
import net.novatech.jbserver.world.provider.BaseWorldProvider;
/*
 * To question what the hack is this, there is simple question: it will be new format for minigame worlds,
 * based on Hypixel's slime format.
 * 
 */
public class NPWorldWorldProvider extends BaseWorldProvider{
	
	@Getter
	public File worldFile;
	
	public NPWorldWorldProvider(String path) {
		super(path);
		this.worldFile = new File(getAbsolutePath() + ".npworld");
	}

	@Override
	public boolean isValid() {
		if(new File(getAbsolutePath() + ".npworld").exists()) {
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