package net.novaplay.jbserver.factory;

import java.util.HashMap;
import java.util.UUID;

import net.novaplay.jbserver.player.Player;
import net.novaplay.jbserver.server.Server;

public class PlayerFactory implements Factory {

	private HashMap<UUID,Player> players = new HashMap<UUID,Player>();
	
	public void init(FactoryManager mgr) {
		
	}
	
	public int getPlayerCount() {
		return getOnlinePlayers().size();
	}
	
	public HashMap<UUID,Player> getOnlinePlayers(){
		return this.players;
	}
	
	public int getMaximalPlayerCount() {
		return Server.getInstance().getServerSettings().isDynamicPlayerCountEnabled() ? getPlayerCount() + 1 : Server.getInstance().getServerSettings().getMaxPlayerCount();
	}
	
	public boolean addPlayer(Player player) {
		return false;
	}
	
	public boolean removePlayer() {
		return false;
	}

}
