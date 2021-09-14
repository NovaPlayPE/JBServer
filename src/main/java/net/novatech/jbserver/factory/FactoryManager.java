package net.novatech.jbserver.factory;

import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.utils.Color;

public class FactoryManager {
	
	public Server server = null;
	public Server getServer() {
		return this.server;
	}
	
	public Factory blockFactory;
	public Factory entityFactory;
	public Factory blockEntityFactory;
	public Factory itemFactory;
	public Factory commandFactory;
	public Factory worldFactory;
	public Factory playerFactory;
	public Factory generatorFactory;
	
	public void init(Server server) {
		this.server = server;
		
		this.server.getLogger().info(Color.GREEN + "Enabling " + Color.WHITE + "Entity" + Color.GREEN + " factory");
		entityFactory = new EntityFactory();
		entityFactory.init(this);
		

		this.server.getLogger().info(Color.GREEN + "Enabling " + Color.WHITE + "World" + Color.GREEN + " factory");
		worldFactory = new WorldFactory();
		worldFactory.init(this);
		

		this.server.getLogger().info(Color.GREEN + "Enabling " + Color.WHITE + "Player" + Color.GREEN + " factory");
		playerFactory = new PlayerFactory();
		playerFactory.init(this);
		
	}
	
	public EntityFactory getEntityFactory() {
		return (EntityFactory)entityFactory;
	}
	
	public PlayerFactory getPlayerFactory() {
		return (PlayerFactory) playerFactory;
	}
	
	public WorldFactory getWorldFactory() {
		return (WorldFactory) worldFactory;
	}
	
}