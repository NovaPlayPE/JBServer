package net.novaplay.jbserver.world.chunk;

import net.novaplay.library.nbt.tags.*;
import net.novaplay.jbserver.entity.Entity;
import net.novaplay.jbserver.world.World;


import java.util.*;

import lombok.Getter;
import lombok.Setter;

public class Chunk {
	
	@Getter
	public int x;
	@Getter
	public int z;
	@Getter
	public World world;
	public Section[] chunkSections;
	
	public List<Entity> entities = new ArrayList<Entity>();
	
	public List<CompoundTag> nbtEntities = new ArrayList<CompoundTag>();
	public List<CompoundTag> nbtBlockEntities = new ArrayList<CompoundTag>();
	
	public Chunk(int x, int z, World world) {
		this.x = x;
		this.z = z;
		
		this.world = world;
		this.chunkSections = new Section[16];
	}
	
	public byte getBlockLight(int x, int y, int z) {
		int index = getSubChunkIndex(y);
		return getSections()[index].getBlockLight(x,y,z);
	}
	
	public byte getSkyLight(int x, int y, int z) {
		int index = getSubChunkIndex(y);
		Section sec = getSections()[index];
		return sec.getSkyLight(x, y, z);
	}
	
	public void setBlockLight(int x, int y, int z, byte value) {
		int index = getSubChunkIndex(y);
		getSections()[index].setBlockLight(x, y, z, value);
	}
	
	public void setSkyLight(int x, int y, int z, byte value) {
		int index = getSubChunkIndex(y);
		getSections()[index].setSkyLight(x,y,z,value);
	}
	
	public int getSubChunkIndex(int y) {
		return y >> 4;
	}
	
	public Section[] getSections() {
		return chunkSections;
	}
	
	public void save() {
		
	}
	
}