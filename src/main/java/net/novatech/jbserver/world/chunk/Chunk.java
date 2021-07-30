package net.novatech.jbserver.world.chunk;

import net.novatech.jbserver.entity.Entity;
import net.novatech.jbserver.material.MaterialBlock;
import net.novatech.jbserver.world.World;
import net.novatech.library.math.Vector3i;
import net.novatech.library.nbt.tags.*;

import java.util.*;

import lombok.Getter;
import lombok.Setter;

public class Chunk {
	
	@Getter
	public ChunkVector vector;
	@Getter
	public World world;
	public ChunkSection[] chunkSections;
	
	public List<Entity> entities = new ArrayList<Entity>();
	
	public List<CompoundTag> nbtEntities = new ArrayList<CompoundTag>();
	public List<CompoundTag> nbtBlockEntities = new ArrayList<CompoundTag>();
	
	public Chunk(ChunkVector vector, World world) {
		this.vector = vector;
		
		this.world = world;
		this.chunkSections = new ChunkSection[16];
	}
	
	public MaterialBlock getBlockAt(Vector3i vector) {
		return getBlockAt(vector.getX(), vector.getY(), vector.getZ());
	}
	
	public MaterialBlock getBlockAt(int x, int y, int z) {
		return null;
	}
	
	public void setBlock(MaterialBlock block) {
		setBlock(block, block.getVector().getX(), block.getVector().getY(), block.getVector().getZ());
	}
	
	public void setBlock(MaterialBlock block, int x, int y, int z) {
		
	}
	
	public byte getBlockLight(int x, int y, int z) {
		int index = getSubChunkIndex(y);
		return getSections()[index].getBlockLight(x,y,z);
	}
	
	public byte getSkyLight(int x, int y, int z) {
		int index = getSubChunkIndex(y);
		ChunkSection sec = getSections()[index];
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
	
	public ChunkSection[] getSections() {
		return chunkSections;
	}
	
	public void save() {
		
	}
	
}