package net.novatech.jbserver.world;

import java.util.*;

import lombok.Getter;
import lombok.Setter;

import net.novatech.library.math.Vector3d;
import net.novatech.library.nbt.tags.*;
import net.novatech.library.nbt.NBTIO;

public class WorldData {
	
	@Getter
	@Setter
	public String worldName;
	
	@Getter
	@Setter
	public int time = 0;
	
	@Getter
	@Setter
	public boolean timeEnabled = true;
	
	@Getter
	@Setter
	public long randomSeed = 0L;
	
	@Getter
	@Setter
	public String generatorName ;
	
	@Getter
	@Setter
	public String generatorOptions;
	
	@Getter
	@Setter
	public int generatorVersion;
	
	@Getter
	@Setter
	public Vector3d spawn;
	
	@Getter
	@Setter
	public boolean raining = false;
	
	@Getter
	@Setter
	public int rainingTime = 0;
	
	@Getter
	@Setter
	public boolean thundering = false;
	
	@Getter
	@Setter
	public int thunderingTime = 0;
	
	public static WorldData readFromNbt(CompoundTag nbt) {
		WorldData data = new WorldData();
		
		CompoundTag tag = nbt.getCompoundValue("Data");
		data.setSpawn(new Vector3d(
				tag.getDoubleValue("SpawnX").getValue(),
				tag.getDoubleValue("SpawnY").getValue(),
				tag.getDoubleValue("SpawnZ").getValue()
			));
		data.setWorldName(tag.getStringValue("WorldName").getValue());
		data.setTime(tag.getIntValue("Time").getValue());
		data.setRandomSeed(tag.getLongValue("RandomSeed").getValue());
		return data;
	}
	
	public static CompoundTag writeToNbt(WorldData data) {
		CompoundTag tag = new CompoundTag("Data");
		tag.add(new DoubleTag("SpawnX", data.getSpawn().getX()));
		tag.add(new DoubleTag("SpawnY", data.getSpawn().getY()));
		tag.add(new DoubleTag("SpawnZ", data.getSpawn().getZ()));
		tag.add(new StringTag("WorldName", tag.getName()));
		tag.add(new IntTag("Time", data.getTime()));
		tag.add(new LongTag("RandomSeed", data.getRandomSeed()));
		
		CompoundTag nbt = new CompoundTag("");
		nbt.add(tag);
		
		return nbt;
	}
}