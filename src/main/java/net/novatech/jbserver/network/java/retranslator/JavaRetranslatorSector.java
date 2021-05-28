package net.novatech.jbserver.network.java.retranslator;

import java.util.HashMap;

import net.novatech.jbprotocol.java.packets.JavaPacket;
import net.novatech.jbserver.network.java.retranslator.impl.ChunkDataRetranslator;
import net.novatech.jbserver.network.java.retranslator.impl.EntityCreateRetranslator;
import net.novatech.jbserver.network.java.retranslator.impl.TextRetranslator;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.JBPacketIdentifier;

public class JavaRetranslatorSector {
	
	private static HashMap<JBPacketIdentifier, JavaRetranslator> MAP = new HashMap<JBPacketIdentifier,JavaRetranslator>();
	private static HashMap<Class<? extends JavaPacket>, JBPacketIdentifier> PAK = new HashMap<Class<? extends JavaPacket>,JBPacketIdentifier>();
	static {
		MAP.put(JBPacketIdentifier.CHUNK_DATA, new ChunkDataRetranslator());
		MAP.put(JBPacketIdentifier.SEND_CHAT_PACKET, new TextRetranslator());
		MAP.put(JBPacketIdentifier.CREATE_ENTITY, new EntityCreateRetranslator());
		
	}
	
	public static JavaPacket translateTo(JBPacket pk) {
		return MAP.get(pk.getIdentifier()).translate(pk);
	}

}
