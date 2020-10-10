package net.novaplay.jbserver.factory;

import net.novaplay.jbserver.server.Server;
import net.novaplay.jbserver.world.World;
import net.novaplay.jbserver.world.WorldData;
import net.novaplay.jbserver.world.WorldType;
import net.novaplay.jbserver.world.provider.impl.BaseWorldProvider;
import net.novaplay.jbserver.world.provider.impl.anvil.AnvilWorldProvider;
import net.novaplay.library.math.Vector3i;

import java.util.*;

public class WorldFactory implements Factory {
	
	public World defaultWorld = null;
	public Map<String, World> worlds = new HashMap<String, World>();
	
	public void init(FactoryManager manager) {
		
	}

	public World setDefaultWorld(World world) {
		return this.defaultWorld = world;
	}
	
	public World getDefaultWorld() {
		return this.defaultWorld;
	}
	
	public World loadWorld(String worldname) {
		return loadWorld(worldname, new AnvilWorldProvider(PathManager.getWorldPath() + worldname));
	}
	
	public World loadWorld(String worldname, BaseWorldProvider worldProvider) {
		World world = new World(Server.getInstance(),worldname, worldProvider);
		return world;
	}
	
	public World createNewWorld(String worldname, WorldData data) {
		return createNewWorld(worldname, data, null);
	}
	
	
	public World createNewWorld(String worldname, WorldData data, BaseWorldProvider provider) {
		World world = new World(Server.getInstance(),worldname,provider);
		worlds.put(worldname, world);
		return world;
	}
	
	public void unloadWorld(String worldname) {
		if(worlds.containsKey(worldname)) {
			worlds.get(worldname).unload();
		}
	}
	
	public World getWorldByName(String name) throws NullPointerException {
		if(worlds.containsKey(name)) {
			return worlds.get(name);
		}
		return null;
	}
	
}