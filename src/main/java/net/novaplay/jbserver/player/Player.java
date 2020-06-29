package net.novaplay.jbserver.player;

import net.novaplay.jbserver.command.CommandSender;
import net.novaplay.jbserver.network.NetworkSession;

public interface Player extends CommandSender{
	
	NetworkSession getSession();
	PlayerInfo getPlayerInfo();
}
