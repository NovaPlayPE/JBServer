package net.novatech.jbserver.command.impl;

import java.util.ArrayList;

import net.novatech.jbserver.command.Command;
import net.novatech.jbserver.command.CommandSender;
import net.novatech.jbserver.command.ConsoleCommandSender;
import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.utils.ConsoleColor;

public class HelpCommand extends Command {

	public HelpCommand(String name) {
		super(name);
		this.aliases.add("?");
		this.description = "Help command shows all available commands";
		this.usage = "help";
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		for(Command commands : Server.getInstance().getCommandMap().getCommands()) {
			if(sender instanceof ConsoleCommandSender) {
				sender.sendMessage(ConsoleColor.YELLOW + commands.getName() + ConsoleColor.GREEN + " - " + ConsoleColor.WHITE + commands.getDescription());
			} else if(sender instanceof Player) {
				
			}
		}
		return false;
	}

}
