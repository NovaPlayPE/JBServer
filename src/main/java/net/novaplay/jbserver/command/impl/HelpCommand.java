package net.novaplay.jbserver.command.impl;

import java.util.ArrayList;

import net.novaplay.jbserver.command.Command;
import net.novaplay.jbserver.command.CommandSender;
import net.novaplay.jbserver.command.ConsoleCommandSender;
import net.novaplay.jbserver.player.Player;
import net.novaplay.jbserver.server.Server;
import net.novaplay.jbserver.utils.ConsoleColor;

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
