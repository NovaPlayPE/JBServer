package net.novatech.jbserver.network.bedrock.retranslator.impl;

import net.novatech.jbprotocol.bedrock.packets.AddActorPacket;
import net.novatech.jbprotocol.bedrock.packets.BedrockPacket;
import net.novatech.jbserver.network.bedrock.retranslator.BedrockRetranslator;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.impl.JBCreateEntityPacket;
import net.novatech.library.math.Vector3f;

public class EntityCreateRetranslator extends BedrockRetranslator<AddActorPacket> {


	@Override
	public AddActorPacket translate(JBPacket pk) {
		JBCreateEntityPacket jb = (JBCreateEntityPacket)pk;
		
		AddActorPacket packet = new AddActorPacket();
		packet.runtimeId = packet.uniqueId = jb.id;
		packet.type = jb.entityType.getIdentifier();
		packet.position = (Vector3f) jb.position;
		packet.rotation = jb.rotation;
		packet.motion = (Vector3f) jb.motion;
		
		return packet;
	}

}
