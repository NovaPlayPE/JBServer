package net.novatech.jbserver.player.bedrock;

import java.util.UUID;

import net.novatech.jbserver.player.PlayerInfo;

public class BedrockPlayerInfo extends PlayerInfo {

	public BedrockPlayerInfo(String username, String address, int port, UUID uuid) {
		super(username, address,port, uuid);
	}

}
