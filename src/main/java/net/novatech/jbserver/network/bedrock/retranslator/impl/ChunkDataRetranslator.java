package net.novatech.jbserver.network.bedrock.retranslator.impl;

import net.novatech.jbserver.network.bedrock.retranslator.BedrockRetranslator;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.impl.JBChunkDataPacket;
import net.novatech.jbprotocol.bedrock.data.BedrockChunk;
import net.novatech.jbprotocol.bedrock.data.BedrockChunkData;
import net.novatech.jbprotocol.bedrock.packets.BedrockPacket;
import net.novatech.jbprotocol.bedrock.packets.LevelChunkPacket;

public class ChunkDataRetranslator extends BedrockRetranslator<LevelChunkPacket> {

	@Override
	public LevelChunkPacket translate(JBPacket pk) {
		JBChunkDataPacket p = (JBChunkDataPacket)pk;
		
		BedrockChunk chunk = new BedrockChunk();
		chunk.chunkX = p.getChunk().getX();
		chunk.chunkZ = p.getChunk().getZ();
		chunk.chunkData = new BedrockChunkData();
		
		LevelChunkPacket levelChunk = new LevelChunkPacket();
		levelChunk.chunk = chunk;
		
		return levelChunk;
	}

}
