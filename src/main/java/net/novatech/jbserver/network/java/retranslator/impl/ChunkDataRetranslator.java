package net.novatech.jbserver.network.java.retranslator.impl;

import com.github.steveice10.mc.protocol.data.game.chunk.Chunk;
import com.github.steveice10.mc.protocol.data.game.chunk.Column;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.packetlib.packet.Packet;

import net.novatech.jbserver.network.java.retranslator.JavaRetranslator;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.impl.JBChunkDataPacket;

public class ChunkDataRetranslator extends JavaRetranslator {

	@Override
	public JBPacket translateFrom(Packet pk) {
		ServerChunkDataPacket p = (ServerChunkDataPacket)pk;
		
		JBChunkDataPacket packet = new JBChunkDataPacket();
		packet.setChunkX(p.getColumn().getX());
		packet.setChunkZ(p.getColumn().getZ());
		
		return packet;
	}

	
	@Override
	public Packet translateTo(JBPacket pk) {
		JBChunkDataPacket p = (JBChunkDataPacket)pk;
		ServerChunkDataPacket packet = new ServerChunkDataPacket(new Column(p.getChunkX(),p.getChunkZ(), new Chunk[0], new CompoundTag[0],new CompoundTag("kek")));
		
		return packet;
	}

}
