package net.novaplay.jbserver.world;

import net.novaplay.jbserver.server.Server;
import net.novaplay.jbserver.world.provider.WorldProvider;

public class WorldManager {

	public World createNewWorld(String worldname, WorldProvider provider) {
		return createNewWorld(worldname, Server.getInstance().getWorldPath(), provider);
	}
	
	public World createNewWorld(String worldname, String path, WorldProvider provider) {
		return null;
	}
	
}