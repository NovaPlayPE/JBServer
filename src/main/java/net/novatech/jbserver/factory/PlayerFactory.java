package net.novatech.jbserver.factory;

import java.util.HashMap;
import java.util.UUID;

import net.novatech.jbprotocol.GameSession;

import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.network.NetworkSession;
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
	
	public Player searchPlayerBySession(GameSession session) {
		for(Player p : getOnlinePlayers().values()) {
			NetworkSession network = p.getSession();
			if(network.getServerSession() == session) {
				return p;
			}
		}
		return null;
	}
	
	public boolean addPlayer(Player player) {
		this.players.put(player.getPlayerInfo().getUniqueId(), player);
		return true;
	}
	
	public boolean removePlayer(Player player) {
		this.players.remove(player.getPlayerInfo().getUniqueId());
		return true;
	}

}
