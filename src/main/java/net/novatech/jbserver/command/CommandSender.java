package net.novatech.jbserver.command;

import net.novatech.jbserver.administration.ServerAdministrator;

public interface CommandSender extends ServerAdministrator{

	void sendMessage(String message);
	
}
