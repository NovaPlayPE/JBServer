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
		return server.getServerSettings().isDynamicPlayerCountEnabled() ? getPlayerCount() + 1 : server.getServerSettings().getMaxPlayerCount();
	}
	
	public boolean addPlayer(Player player) {
		return false;
	}
	
	public boolean removePlayer() {
		return false;
	}
	
}