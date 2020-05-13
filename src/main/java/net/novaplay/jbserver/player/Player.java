package net.novaplay.jbserver.player;

import net.novaplay.jbserver.network.NetworkSession;

public interface Player {

	boolean connected = false;

	NetworkSession getSession();

	PlayerInfo getPlayerInfo();

	boolean isConnected();
}
