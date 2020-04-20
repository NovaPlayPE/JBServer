package net.novaplay.jbserver.player;

import java.util.HashMap;
import java.util.UUID;

import net.novaplay.jbserver.server.Server;

public class PlayerManager {
	
	private HashMap<UUID,Player> players = new HashMap<UUID,Player>();
	private Server server = null;
	
	public PlayerManager(Server server) {
		this.server = server;
	}
	
	public int getPlayerCount() {
		return getOnlinePlayers().size();
	}
	
	public HashMap<UUID,Player> getOnlinePlayers(){
		return this.players;
	}
	
	public int getMaximalPlayerCount() {
		return server.getPropertyBoolean("max-players-plus-1",false) ? getPlayerCount() + 1 : server.getPropertyInt("max-players",40);
	}
	
	public boolean addPlayer() {
		return false;
	}
	
	public boolean removePlayer() {
		return false;
	}
	
}