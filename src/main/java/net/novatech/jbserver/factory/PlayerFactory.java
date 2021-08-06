package net.novatech.jbserver.factory;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.UUID;

import net.novatech.jbprotocol.GameSession;
import net.novatech.jbprotocol.auth.GameProfile;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.player.PlayerInfo;
import net.novatech.jbserver.player.bedrock.BedrockPlayer;
import net.novatech.jbserver.player.java.JavaPlayer;
import net.novatech.jbserver.player.java.JavaPlayerInfo;
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
	
	public Player getPlayerByUUID(UUID uid) {
		for(Player p : getOnlinePlayers().values()) {
			if(p.getPlayerInfo().getUniqueId() == uid) {
				return p;
			}
		}
		return null;
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
	
	public GameProfile[] convertToPongData() {
		GameProfile[] profiles = new GameProfile[getPlayerCount()];
		int i = 0;
		for(Player p : getOnlinePlayers().values()) {
			PlayerInfo info = p.getPlayerInfo();
			if(p instanceof JavaPlayer) {
				profiles[i] = ((JavaPlayerInfo)info).getProfile();
				i++;
				break;
			} else if(p instanceof BedrockPlayer){
				profiles[i] = new GameProfile(
						UUID.nameUUIDFromBytes(("OfflinePlayer:"+info.getName()).getBytes(StandardCharsets.UTF_8)),
						info.getName());
				i++;
				break;
			}
		}
		return profiles;
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
