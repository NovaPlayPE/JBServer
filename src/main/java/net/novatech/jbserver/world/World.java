package net.novatech.jbserver.world;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import net.novatech.jbserver.material.MaterialBlock;
import net.novatech.jbserver.network.protocol.impl.JBChunkDataPacket;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.world.chunk.Chunk;
import net.novatech.jbserver.world.chunk.ChunkManager;
import net.novatech.jbserver.world.chunk.ChunkVector;
import net.novatech.jbserver.world.provider.BaseWorldProvider;
import net.novatech.library.math.Vector3d;

public class World {
	
	@Getter
	private Server server = null;
	
	@Getter
	private String name = null;
	
	@Getter
	private BaseWorldProvider worldProvider = null;
	
	@Getter
	private Vector3d worldSpawn = null;
	
	@Getter
	private ChunkManager chunkManager = null;
	private Map<String, Player> activePlayers = new HashMap<String, Player>();
	
	public World(Server server, String worldId, BaseWorldProvider provider) {
		this.server = server;
		
		this.name = worldId;
		this.worldProvider = provider; 
		
		this.chunkManager = new ChunkManager(this, provider);
	}
	
	public void init() {
		
	}
	
	public void load() {
		this.worldProvider.load();
		
		WorldData worldData = this.worldProvider.getWorldData();
	}
	
	public void unload() {
		
	}
	
	public void save() {
		
	}
	
	public void tick(int currentTick) {
		
	}
	
	public synchronized void loadChunksForPlayer(Player player) {
		if(Server.getInstance().getServerSettings().useInMemoryWorlds()) {
			for(Player p : this.activePlayers.values()) {
				sendChunks(p);
			}
		} else {
			
		}
	}
	
	public synchronized void sendChunks(Player player) {
		for(Chunk chunk : getChunkManager().getChunkCache().cachedChunks) {
			
		}
	}
	
	public synchronized void sendChunks(Player player, ChunkVector vector) {
		JBChunkDataPacket pk = new JBChunkDataPacket();
		pk.chunk = getChunkManager().getChunk(vector);
		
		player.getSession().sendPacket(pk);
	}
	
}