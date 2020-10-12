package net.novatech.jbserver.player.bedrock;

import java.util.UUID;

import net.novatech.jbserver.player.PlayerInfo;

public class BedrockPlayerInfo extends PlayerInfo {

	public BedrockPlayerInfo(String address, int port, UUID uuid) {
		super(address,port, uuid);
	}

}
