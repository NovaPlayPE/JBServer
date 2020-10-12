package net.novatech.jbserver.player;

import net.novatech.jbserver.command.CommandSender;
import net.novatech.jbserver.network.NetworkSession;

public interface Player extends CommandSender{
	
	NetworkSession getSession();
	PlayerInfo getPlayerInfo();
}
