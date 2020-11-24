package net.novatech.jbserver.network.java.retranslator;

import java.util.HashMap;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;
import com.github.steveice10.packetlib.packet.Packet;

import net.novatech.jbserver.network.java.retranslator.impl.ChunkDataRetranslator;
import net.novatech.jbserver.network.java.retranslator.impl.TextRetranslator;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.JBPacketIdentifier;

public class JavaRetranslatorSector {
	
	private static HashMap<JBPacketIdentifier, JavaRetranslator> MAP = new HashMap<JBPacketIdentifier,JavaRetranslator>();
	private static HashMap<Class<? extends Packet>, JBPacketIdentifier> PAK = new HashMap<Class<? extends Packet>,JBPacketIdentifier>();
	static {
		MAP.put(JBPacketIdentifier.CHUNK_DATA, new ChunkDataRetranslator());
		MAP.put(JBPacketIdentifier.TEXT_PACKET, new TextRetranslator());
		
		PAK.put(ServerChunkDataPacket.class, JBPacketIdentifier.CHUNK_DATA);
		PAK.put(ServerChatPacket.class, JBPacketIdentifier.TEXT_PACKET);
		
	}
	
	public static Packet translateTo(JBPacket pk) {
		return MAP.get(pk.getIdentifier()).translateTo(pk);
	}
	
	public static JBPacket translateFrom(Packet pk) {
		return MAP.get(PAK.get(pk.getClass())).translateFrom(pk);
	}

}
