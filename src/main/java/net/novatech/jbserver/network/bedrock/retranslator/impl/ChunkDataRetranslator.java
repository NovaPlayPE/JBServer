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
		chunk.chunkX = p.getChunk().getVector().getX();
		chunk.chunkZ = p.getChunk().getVector().getZ();
		BedrockChunkData data = new BedrockChunkData();
		int subChunks = p.getChunk().getSections().length;
		while(subChunks >= 0 && p.getChunk().getSections()[subChunks] == null) {
			subChunks--;
		}
		data.subChunkCount = subChunks;
		
		chunk.chunkData = data;
		
		LevelChunkPacket levelChunk = new LevelChunkPacket();
		levelChunk.chunk = chunk;
		
		return levelChunk;
	}

}
