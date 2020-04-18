package net.novaplay.jbserver.network.bedrock.retranslator;

import java.util.HashMap;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.*;

import net.novaplay.jbserver.network.bedrock.retranslator.impl.*;
import net.novaplay.jbserver.network.protocol.JBPacket;
import net.novaplay.jbserver.network.protocol.JBPacketIdentifier;

public class BedrockRetranslatorSector {
	
	private static HashMap<JBPacketIdentifier, BedrockRetranslator> MAP = new HashMap<JBPacketIdentifier,BedrockRetranslator>();
	private static HashMap<Class<? extends BedrockPacket>, JBPacketIdentifier> PAK = new HashMap<Class<? extends BedrockPacket>,JBPacketIdentifier>();
	static {
		PAK.put(LoginPacket.class, JBPacketIdentifier.LOGIN);
		
		MAP.put(JBPacketIdentifier.LOGIN, new LoginRetranslator());
	}
	
	public static BedrockPacket translateTo(JBPacket pk) {
		return MAP.get(pk.getIdentifier()).translateTo(pk);
	}
	
	public static JBPacket translateFrom(BedrockPacket pk) {
		return MAP.get(PAK.get(pk.getClass())).translateFrom(pk);
	}

}
