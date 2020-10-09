package net.novaplay.jbserver.world.provider.impl.npworld;

import java.io.File;

import lombok.Getter;

import net.novaplay.jbserver.world.WorldData;
import net.novaplay.jbserver.world.chunk.Chunk;
import net.novaplay.jbserver.world.provider.WorldProvider;
import net.novaplay.jbserver.world.provider.impl.BaseWorldProvider;
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
		this.worldFile = new File(path + ".npworld");
	}

	@Override
	public boolean isValid() {
		if(new File(getPath() + ".npworld").exists()) {
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