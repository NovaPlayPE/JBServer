package net.novaplay.jbserver.world;

import lombok.Getter;
import lombok.Setter;

public class WorldBuilder {
	
	public WorldBuilder() {}
	
	@Getter
	@Setter
	public String name = "world";
	
	@Getter
	@Setter
	public long seed = 0L;
	
	
	
}