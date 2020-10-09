package net.novaplay.jbserver.world.chunk;

import net.novaplay.library.nbt.tags.*;
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
	
	public List<CompoundTag> nbtEntities = new ArrayList<CompoundTag>();
	public List<CompoundTag> nbtBlockEntities = new ArrayList<CompoundTag>();
	
	public Chunk(int x, int z, World world) {
		this.x = x;
		this.z = z;
		
		this.world = world;
		this.chunkSections = new Section[16];
	}
	
	public Section[] getSections() {
		return chunkSections;
	}
	
	public void save() {
		
	}
	
}