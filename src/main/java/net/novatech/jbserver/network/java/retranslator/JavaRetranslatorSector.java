package net.novatech.jbserver.network.java.retranslator;

import java.util.HashMap;

import net.novatech.jbprotocol.java.packets.JavaPacket;
import net.novatech.jbserver.network.java.retranslator.impl.ChunkDataRetranslator;
import net.novatech.jbserver.network.java.retranslator.impl.TextRetranslator;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.JBPacketIdentifier;

public class JavaRetranslatorSector {
	
	private static HashMap<JBPacketIdentifier, JavaRetranslator> MAP = new HashMap<JBPacketIdentifier,JavaRetranslator>();
	private static HashMap<Class<? extends JavaPacket>, JBPacketIdentifier> PAK = new HashMap<Class<? extends JavaPacket>,JBPacketIdentifier>();
	static {
		MAP.put(JBPacketIdentifier.CHUNK_DATA, new ChunkDataRetranslator());
		MAP.put(JBPacketIdentifier.SEND_CHAT_PACKET, new TextRetranslator());
		
	}
	
	public static JavaPacket translateTo(JBPacket pk) {
		return MAP.get(pk.getIdentifier()).translateTo(pk);
	}
	
	public static JBPacket translateFrom(JavaPacket pk) {
		return MAP.get(PAK.get(pk.getClass())).translateFrom(pk);
	}

}
