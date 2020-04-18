package net.novaplay.jbserver.command;

import net.novaplay.jbserver.administration.ServerAdministrator;

public interface CommandSender extends ServerAdministrator{

	void sendMessage(String message);
	
}
