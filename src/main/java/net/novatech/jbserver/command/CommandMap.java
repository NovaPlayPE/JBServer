package net.novatech.jbserver.command;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.Setter;
import net.novatech.jbserver.server.Server;

public class CommandMap {
	
	@Getter
	public Server server = null;
	
	public List<Command> registeredCommands = new ArrayList<Command>();
	
	public CommandMap(Server server) {
		this.server = server;
	}
	
	public void registerCommand(Command command) {
		for(Command commands : registeredCommands) {
			if(command.getName().equals(commands.getName().toLowerCase())) {
				this.server.getLogger().error("Command with same name ("+command.getName()+") already exists");
				return;
			}
			if(commands.getAliases().equals(command.getName().toLowerCase())) {
				this.server.getLogger().error("Command " + commands.getName() + " already has alias " + command.getName());
				return;
			}
			for(String alias : command.getAliases()) {
				if(commands.getAliases().contains(alias)) {
					this.server.getLogger().error("Alias " + alias + " is registered in " + commands.getName() + ", tried register from " + command.getName());
					return;
				}
			}
		}
		registeredCommands.add(command);
	}
	
	public List<Command> getCommands(){
		return this.registeredCommands;
	}
	
	public String executeCommand(CommandSender sender, String commandLine) {
		String[] line = commandLine.split(" ");
		String label = line[0];
		Command cmd = getCommand(label);
		if(cmd == null) {
			return label;
		}
		ArrayList<String> arguments = new ArrayList<String>();
		for(String arg : commandLine.substring(label.length()).split(" ")){
			if(arg.equalsIgnoreCase("") || arg.equalsIgnoreCase(" ")) {
				continue;
			}
			arguments.add(arg);
		}
		boolean usage = cmd.execute(sender,label,arguments.toArray(new String[arguments.size()]));
		if(usage) {
			sender.sendMessage(cmd.getUsage());
		}
		return null;
	}
	
	public Command getCommand(String cmd) {
		for(Command command : registeredCommands) {
			if(command.getName().equals(cmd.toLowerCase())) {
				return command;
			}
			else if(command.getAliases().contains(cmd.toLowerCase())) {
				return command;
			}
		}
		return null;
	}
	
	public void dispatch(CommandSender sender, Consumer<String> consumer) {
		try {
			String line;
			while((line = getServer().getLogger().getConsole().readLine()) != null) {
				if(!line.equalsIgnoreCase("")) {
					String command = executeCommand(sender,line);
					if(command != null) {
						consumer.accept(executeCommand(sender,command));
					}
				}
			}
		} catch(IOException e) {
			getServer().getLogger().logException(e);
		}
		this.dispatch(sender,consumer);
	}

}
