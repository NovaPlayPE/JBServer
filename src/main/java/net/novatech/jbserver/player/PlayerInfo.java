package net.novatech.jbserver.player;

import java.util.UUID;

public abstract class PlayerInfo {

	private String username;
	private String address = "0.0.0.0";
	private int port;
	private UUID uuid = null;
	
	
	public PlayerInfo(String username, String address, int port, UUID uuid) {
		this.username = username;
		this.address = address;
		this.port = port;
		this.uuid = uuid;
	}
	
	public String getName() {
		return this.username;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public int getPort() {
		return this.port;
	}
	
	public UUID getUniqueId() {
		return this.uuid;
	}
	
}