package net.novatech.jbserver.factory;

import net.novatech.jbserver.event.world.WorldLoadEvent;
import net.novatech.jbserver.manager.PathManager;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.utils.Color;
import net.novatech.jbserver.world.World;
import net.novatech.jbserver.world.WorldData;
import net.novatech.jbserver.world.WorldType;
import net.novatech.jbserver.world.provider.BaseWorldProvider;
import net.novatech.jbserver.world.provider.WorldProviderManager;
import net.novatech.jbserver.world.provider.impl.AnvilWorldProvider;
import net.novatech.jbserver.world.provider.impl.LevelDBWorldProvider;
import net.novatech.jbserver.world.provider.impl.NPWorldWorldProvider;
import net.novatech.library.math.vector.Vector3d;
import net.novatech.library.math.vector.Vector3i;

import java.util.*;

public class WorldFactory implements Factory {
	
	public World defaultWorld = null;
	public Map<String, World> worlds = new HashMap<String, World>();
	
	public void init(FactoryManager manager) {
		WorldProviderManager.registerWorldProvider(WorldProviderManager.ANVIL, AnvilWorldProvider.class);
		Server.getInstance().getLogger().debug(Color.GREEN + "Loaded Anvil provider");
		WorldProviderManager.registerWorldProvider(WorldProviderManager.LEVELDB, LevelDBWorldProvider.class);
		Server.getInstance().getLogger().debug(Color.GREEN + "Loaded LevelDB provider");
		WorldProviderManager.registerWorldProvider(WorldProviderManager.NPWORLD, NPWorldWorldProvider.class);
		Server.getInstance().getLogger().debug(Color.GREEN + "Loaded NPWorld provider");
		
		String worldName = manager.getServer().getServerSettings().getDefaultWorldName();
		setDefaultWorld(doesWorldExist(worldName) ? loadWorld(worldName) : createNewWorld(worldName, getDefaultWorldData()));
	}
	
	public boolean doesWorldExist(String worldname) {
		return doesWorldExist(worldname, new AnvilWorldProvider(worldname));
	}
	
	public boolean doesWorldExist(String worldname, BaseWorldProvider worldProvider) {
		return worldProvider.isValid();
	}

	public World setDefaultWorld(World world) {
		return this.defaultWorld = world;
	}
	
	public World getDefaultWorld() {
		return this.defaultWorld;
	}
	
	public World loadWorld(String worldname) {
		return loadWorld(worldname, WorldProviderManager.tryGetProvider(worldname));
	}
	
	public World loadWorld(String worldname, BaseWorldProvider worldProvider) {
		long start = System.currentTimeMillis();
		
		World world = new World(Server.getInstance(),worldname, worldProvider);
		worlds.put(worldname, world);
		world.load();
		
		long time = System.currentTimeMillis() - start; // ToDo: check when world is fully loaded
		WorldLoadEvent ev = new WorldLoadEvent(world, time);
		ev.call();
		
		return world;
	}
	
	public World createNewWorld(String worldname, WorldData data) {
		return createNewWorld(worldname, data, WorldProviderManager.getDefaultProvider(Server.getInstance().getServerSettings().getDefaultWorldProvider(), worldname));
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
	
	private WorldData getDefaultWorldData() {
		WorldData data = new WorldData();
		
		data.setSpawn(new Vector3d(128, 70, 128));
		data.setRandomSeed(Server.getInstance().getServerSettings().getDefaultWorldSeed());
		data.setGeneratorName(Server.getInstance().getServerSettings().getDefaultWorldType());
		data.setGeneratorVersion(0);
		data.setTime(6000);
		data.setRaining(false);
		data.setThundering(false);
		
		return data;
	}
	
}