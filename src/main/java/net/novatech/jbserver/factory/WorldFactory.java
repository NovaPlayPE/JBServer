package net.novatech.jbserver.factory;

import net.novatech.jbserver.manager.PathManager;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.world.World;
import net.novatech.jbserver.world.WorldData;
import net.novatech.jbserver.world.WorldType;
import net.novatech.jbserver.world.provider.impl.BaseWorldProvider;
import net.novatech.jbserver.world.provider.impl.anvil.AnvilWorldProvider;
import net.novatech.library.math.Vector3i;

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