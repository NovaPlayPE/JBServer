package net.novatech.jbserver.network.protocol.impl;

import lombok.Getter;
import lombok.Setter;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.JBPacketIdentifier;
import net.novatech.jbserver.world.chunk.ChunkData;

public class JBChunkDataPacket extends JBPacket {
	
	@Override
	public JBPacketIdentifier getIdentifier() {
		return JBPacketIdentifier.CHUNK_DATA;
	}
	
	@Getter
	@Setter
	public int chunkX;
	
	@Getter
	@Setter
	public int chunkZ;
	
	@Getter
	@Setter
	public ChunkData chunkData;
	
	public boolean isServerBound() {
		return false;
	}
	
	public boolean isClientBound() {
		return true;
	}
	
}