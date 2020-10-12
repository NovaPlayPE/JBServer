package net.novatech.jbserver.command;

import java.util.ArrayList;

public abstract class Command {
	
	private String name = "";
	protected ArrayList<String> aliases = new ArrayList<String>();
	protected String usage = "";
	protected String description = "";
	
	public Command(String name) {
		this(name, "");
	}
	
	public Command(String name, String usage) {
		this(name, new ArrayList<String>(),usage);
	}
	
	public Command(String name, ArrayList<String> aliases, String usage) {
		this.name = name;
		this.aliases = aliases;
		this.usage = usage;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<String> getAliases(){
		return this.aliases;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getUsage() {
		return this.usage;
	}
	
	public boolean hasAlias(String alias) {
		if(this.aliases.contains(alias)) {
			return true;
		}
		return false;
	}
	
	public abstract boolean execute(CommandSender sender, String label, String[] args);
	
}
