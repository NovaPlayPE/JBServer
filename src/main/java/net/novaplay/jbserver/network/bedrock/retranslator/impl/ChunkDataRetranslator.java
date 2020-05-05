package net.novaplay.jbserver.network.bedrock.retranslator.impl;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.LevelChunkPacket;

import net.novaplay.jbserver.network.bedrock.retranslator.BedrockRetranslator;
import net.novaplay.jbserver.network.protocol.JBPacket;
import net.novaplay.jbserver.network.protocol.impl.JBChunkDataPacket;

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
		
		LevelChunkPacket packet = new LevelChunkPacket();
		packet.setChunkX(p.getChunkX());
		packet.setChunkZ(p.getChunkZ());
		
		return packet;
	}

}
