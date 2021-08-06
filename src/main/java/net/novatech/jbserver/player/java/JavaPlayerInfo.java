package net.novatech.jbserver.player.java;

import java.util.UUID;

import lombok.Getter;
import net.novatech.jbprotocol.auth.GameProfile;
import net.novatech.jbserver.player.PlayerInfo;

public class JavaPlayerInfo extends PlayerInfo {

	@Getter
	private GameProfile profile;
	
	public JavaPlayerInfo(String username, String address, int port, UUID uuid, GameProfile profile) {
		super(username, address, port, uuid);
		this.profile = profile;
	}

}
