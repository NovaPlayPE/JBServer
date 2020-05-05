package net.novaplay.jbserver.network.java.retranslator.impl;

import com.github.steveice10.mc.protocol.data.game.chunk.Chunk;
import com.github.steveice10.mc.protocol.data.game.chunk.Column;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.packetlib.packet.Packet;

import net.novaplay.jbserver.network.java.retranslator.JavaRetranslator;
import net.novaplay.jbserver.network.protocol.JBPacket;
import net.novaplay.jbserver.network.protocol.impl.JBChunkDataPacket;

public class ChunkDataRetranslator extends JavaRetranslator {

	@Override
	public JBPacket translateFrom(Packet pk) {
		ServerChunkDataPacket p = (ServerChunkDataPacket)pk;
		
		JBChunkDataPacket packet = new JBChunkDataPacket();
		packet.setChunkX(p.getColumn().getX());
		packet.setChunkZ(p.getColumn().getZ());
		
		return packet;
	}

	/**
	 * Yeah yeah, I know, that it is crap,
	 * but I haven't made nbt manager for both libs...
	 */
	
	@Override
	public Packet translateTo(JBPacket pk) {
		JBChunkDataPacket p = (JBChunkDataPacket)pk;
		ServerChunkDataPacket packet = new ServerChunkDataPacket(new Column(p.getChunkX(),p.getChunkZ(), new Chunk[0], new CompoundTag[0],new CompoundTag("kek")));
		
		return packet;
	}

}
