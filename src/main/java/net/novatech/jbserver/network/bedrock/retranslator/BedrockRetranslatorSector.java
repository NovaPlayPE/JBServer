package net.novatech.jbserver.network.bedrock.retranslator;

import java.util.HashMap;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.*;

import net.novatech.jbserver.network.bedrock.retranslator.impl.*;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.JBPacketIdentifier;

public class BedrockRetranslatorSector {
	
	private static HashMap<JBPacketIdentifier, BedrockRetranslator> MAP = new HashMap<JBPacketIdentifier,BedrockRetranslator>();
	private static HashMap<Class<? extends BedrockPacket>, JBPacketIdentifier> PAK = new HashMap<Class<? extends BedrockPacket>,JBPacketIdentifier>();
	static {
		PAK.put(LevelChunkPacket.class, JBPacketIdentifier.CHUNK_DATA);
		PAK.put(TextPacket.class, JBPacketIdentifier.TEXT_PACKET);
		
		MAP.put(JBPacketIdentifier.CHUNK_DATA, new ChunkDataRetranslator());
		MAP.put(JBPacketIdentifier.TEXT_PACKET, new TextRetranslator());
	}
	
	public static BedrockPacket translateTo(JBPacket pk) {
		return MAP.get(pk.getIdentifier()).translateTo(pk);
	}
	
	public static JBPacket translateFrom(BedrockPacket pk) {
		return MAP.get(PAK.get(pk.getClass())).translateFrom(pk);
	}

}
