package net.novatech.jbserver.network.bedrock.retranslator;

import java.util.HashMap;

import net.novatech.jbprotocol.bedrock.packets.BedrockPacket;
import net.novatech.jbprotocol.bedrock.packets.TextPacket;
import net.novatech.jbserver.network.bedrock.retranslator.impl.*;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.JBPacketIdentifier;

public class BedrockRetranslatorSector {
	
	private static HashMap<JBPacketIdentifier, BedrockRetranslator> MAP = new HashMap<JBPacketIdentifier,BedrockRetranslator>();
	
	static {	
		MAP.put(JBPacketIdentifier.CHUNK_DATA, new ChunkDataRetranslator());
		MAP.put(JBPacketIdentifier.SEND_CHAT_PACKET, new TextRetranslator());
		MAP.put(JBPacketIdentifier.CREATE_ENTITY, new EntityCreateRetranslator());
	}
	
	public static BedrockPacket translate(JBPacket pk) {
		return MAP.get(pk.getIdentifier()).translate(pk);
	}

}
