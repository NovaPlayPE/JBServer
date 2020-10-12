package net.novatech.jbserver.world;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import net.novatech.library.math.Vector3i;

public class WorldData {
	
	@Getter
	@Setter
	public String name;
	
	@Getter
	@Setter
	public int time = 0;
	
	@Getter
	@Setter
	public boolean timeEnabled = true;
	
	@Getter
	@Setter
	public long seed = 0L;
	
	@Getter
	@Setter
	public String generatorName ;
	
	@Getter
	@Setter
	public String generatorOptions;
	
	@Getter
	@Setter
	public Vector3i spawn;
	
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
}