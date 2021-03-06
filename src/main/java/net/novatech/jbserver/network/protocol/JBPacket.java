package net.novatech.jbserver.network.protocol;

public abstract class JBPacket {
	
	public abstract JBPacketIdentifier getIdentifier();
	
	public abstract boolean isServerBound();
	public abstract boolean isClientBound();
	
}
