package net.novaplay.jbserver.network.protocol.impl;

import net.novaplay.jbserver.network.protocol.JBPacket;
import net.novaplay.jbserver.network.protocol.JBPacketIdentifier;

import lombok.Getter;
import lombok.Setter;

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
	
	
	
}