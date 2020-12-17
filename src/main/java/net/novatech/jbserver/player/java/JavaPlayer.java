package net.novatech.jbserver.player.java;

import java.io.File;

import net.novatech.jbserver.entity.EntityBuilder;
import net.novatech.jbserver.entity.EntityType;
import net.novatech.jbserver.entity.human.EntityHumanoid;
import net.novatech.jbserver.manager.PathManager;
import net.novatech.jbserver.network.NetworkSession;
import net.novatech.jbserver.network.java.JavaSession;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.player.PlayerInfo;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.utils.Utils;
import net.novatech.library.nbt.NBTIO;
import net.novatech.library.nbt.tags.CompoundTag;

public class JavaPlayer extends Player {

	private NetworkSession session = null;
	private PlayerInfo playerInfo = null;
	
	public JavaPlayer(JavaSession session, PlayerInfo info) {
		this.session = session;
		this.playerInfo = info;
	}
	
	@Override
	public NetworkSession getSession() {
		return this.session;
	}

	@Override
	public PlayerInfo getPlayerInfo() {
		return this.playerInfo;
	}

}
