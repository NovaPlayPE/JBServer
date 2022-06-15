package net.novatech.jbserver.network.protocol.impl;

import net.novatech.jbserver.entity.EntityType;
import net.novatech.jbserver.network.protocol.JBPacket;
import net.novatech.jbserver.network.protocol.JBPacketIdentifier;
import net.novatech.library.math.motion.Rotation;
import net.novatech.library.math.vector.Vector;

public class JBCreateEntityPacket extends JBPacket {
	
	public long id;
	public EntityType entityType;
	public Vector position;
	public Vector motion;
	public Rotation rotation;
	
	@Override
	public JBPacketIdentifier getIdentifier() {
		return JBPacketIdentifier.CREATE_ENTITY;
	}

	@Override
	public boolean isServerBound() {
		return false;
	}

	@Override
	public boolean isClientBound() {
		return true;
	}

}
