package net.novatech.jbserver.factory;

import net.novatech.jbserver.manager.PathManager;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.world.World;
import net.novatech.jbserver.world.WorldData;
import net.novatech.jbserver.world.WorldType;
import net.novatech.jbserver.world.provider.BaseWorldProvider;
import net.novatech.jbserver.world.provider.WorldProviderManager;
import net.novatech.jbserver.world.provider.impl.AnvilWorldProvider;
import net.novatech.jbserver.world.provider.impl.LevelDBWorldProvider;
import net.novatech.jbserver.world.provider.impl.NPWorldWorldProvider;
import net.novatech.library.math.Vector3i;

import java.util.*;

public class WorldFactory implements Factory {
	
	public World defaultWorld = null;
	public Map<String, World> worlds = new HashMap<String, World>();
	
	public void init(FactoryManager manager) {
		WorldProviderManager.registerWorldProvider(WorldProviderManager.ANVIL, AnvilWorldProvider.class);
		WorldProviderManager.registerWorldProvider(WorldProviderManager.LEVELDB, LevelDBWorldProvider.class);
		WorldProviderManager.registerWorldProvider(WorldProviderManager.NPWORLD, NPWorldWorldProvider.class);
	}

	public World setDefaultWorld(World world) {
		return this.defaultWorld = world;
	}
	
	public World getDefaultWorld() {
		return this.defaultWorld;
	}
	
	public World loadWorld(String worldname) {
		return loadWorld(worldname, new AnvilWorldProvider(worldname));
	}
	
	public World loadWorld(String worldname, BaseWorldProvider worldProvider) {
		World world = new World(Server.getInstance(),worldname, worldProvider);
		worlds.put(worldname, world);
		world.load();
		return world;
	}
	
	public World createNewWorld(String worldname, WorldData data) {
		return createNewWorld(worldname, data, null);
	}
	
	
	public World createNewWorld(String worldname, WorldData data, BaseWorldProvider provider) {
		World world = new World(Server.getInstance(),worldname,provider);
		worlds.put(worldname, world);
		world.init();
		return world;
	}
	
	public void unloadWorld(String worldname) {
		if(worlds.containsKey(worldname)) {
			worlds.get(worldname).unload();
			worlds.remove(worldname);
		}
	}
	
	public World getWorldByName(String name) throws NullPointerException {
		if(worlds.containsKey(name)) {
			return worlds.get(name);
		}
		return null;
	}
	
}