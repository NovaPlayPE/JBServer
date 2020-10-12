package net.novatech.jbserver.factory;

import java.util.HashMap;
import java.util.UUID;

import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.server.Server;

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
