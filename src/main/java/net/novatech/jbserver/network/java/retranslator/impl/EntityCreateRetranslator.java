package net.novatech.jbserver.network.java.retranslator.impl;

import net.novatech.jbprotocol.java.packets.play.clientbound.SpawnEntityPacket;
import net.novatech.jbserver.network.java.retranslator.JavaRetranslator;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.impl.JBCreateEntityPacket;
import net.novatech.library.math.Vector3d;
import net.novatech.library.math.Vector3f;

public class EntityCreateRetranslator extends JavaRetranslator<SpawnEntityPacket> {

	@Override
	public SpawnEntityPacket translate(JBPacket pk) {
		JBCreateEntityPacket jb = (JBCreateEntityPacket)pk;
		
		SpawnEntityPacket packet = new SpawnEntityPacket();
		packet.id = (int)jb.id;
		packet.uuid = null; //for now..
		packet.type = jb.entityType.getIntIdentifier();
		packet.position = (Vector3d) jb.position;
		packet.rotation = jb.rotation;
		packet.motion = (Vector3f) jb.motion;
		packet.data = 0;
		
		return packet;
	}

}
