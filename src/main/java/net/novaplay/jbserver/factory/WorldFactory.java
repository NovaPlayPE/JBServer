package net.novaplay.jbserver.factory;

import net.novaplay.jbserver.server.Server;
import net.novaplay.jbserver.world.World;
import net.novaplay.jbserver.world.WorldData;
import net.novaplay.jbserver.world.provider.WorldProvider;

import java.util.*;

public class WorldFactory implements Factory {
	
	public Map<String, World> worlds = new HashMap<String, World>();

	public World createNewWorld(String worldname, WorldData data) {
		return createNewWorld(worldname, data, null);
	}
	
	public World createNewWorld(String worldname, WorldData data, WorldProvider provider) {
		return createNewWorld(worldname, Server.getInstance().getWorldPath(), data, provider);
	}
	
	public World createNewWorld(String worldname, String path, WorldData data, WorldProvider provider) {
		World world = new World(Server.getInstance(),worldname, provider);
	}
	
	public World getWorldByName(String name) throws NullPointerException {
		if(worlds.containsKey(name)) {
			return worlds.get(name);
		}
		return null;
	}
	
	public World
	
}