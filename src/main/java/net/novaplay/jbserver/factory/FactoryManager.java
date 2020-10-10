package net.novaplay.jbserver.factory;

import net.novaplay.jbserver.server.Server;

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
		
		worldFactory = new WorldFactory();
		worldFactory.init(this);
		
		playerFactory = new PlayerFactory();
		playerFactory.init(this);
		
	}
	
	public PlayerFactory getPlayerFactory() {
		return (PlayerFactory) playerFactory;
	}
	
	public WorldFactory getWorldFactory() {
		return (WorldFactory) worldFactory;
	}
	
}