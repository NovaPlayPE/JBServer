package net.novatech.jbserver.network.bedrock.retranslator.impl;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.LevelChunkPacket;

import net.novatech.jbserver.network.bedrock.retranslator.BedrockRetranslator;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.impl.JBChunkDataPacket;
import net.novatech.jbserver.world.chunk.BedrockChunkData;

public class ChunkDataRetranslator extends BedrockRetranslator {

	@Override
	public JBPacket translateFrom(BedrockPacket pk) {
		LevelChunkPacket p = (LevelChunkPacket)pk;
		
		JBChunkDataPacket packet = new JBChunkDataPacket();
		packet.setChunkX(p.getChunkX());
		packet.setChunkZ(p.getChunkZ());
		
		return packet;
	}

	@Override
	public BedrockPacket translateTo(JBPacket pk) {
		JBChunkDataPacket p = (JBChunkDataPacket)pk;
		byte[] data = ((BedrockChunkData)p.getChunkData()).serialize();
		
		LevelChunkPacket packet = new LevelChunkPacket();
		packet.setChunkX(p.getChunkX());
		packet.setChunkZ(p.getChunkZ());
		packet.setData(data);
		
		return packet;
	}

}
