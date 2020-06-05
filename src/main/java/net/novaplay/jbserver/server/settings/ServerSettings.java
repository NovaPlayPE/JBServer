package net.novaplay.jbserver.server.settings;

import net.novaplay.jbserver.config.Config;

public class ServerSettings {
	
	private Config config = null;
	
	public ServerSettings(Config config) {
		this.config = config;
	}
	
	public String getAddress() {
		return this.getPropertyString("server-ip");
	}
	
	public int getJavaPort() {
		return this.getPropertyInt("java-port",25565);
	}
	
	public int getBedrockPort() {
		return this.getPropertyInt("bedrock-port",19132);
	}
	
	public String getMotd() {
		return this.getPropertyString("motd");
	}
	
	public String getMotdUnderline() {
		return this.getPropertyString("motd-underline");
	}
	
	/**
	 * 0 - disabled
	 * more than 0 - enabled
	 */
	public int repeatMotd() {
		return this.getPropertyInt("motd-repeat");
	}
	
	public String getFullMotd() {
		return getFullMotd(repeatMotd());
	}
	
	public String getFullMotd(int spaces) {
		boolean val = false;
		if(Math.abs(spaces) > 0) { 
			val = true;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(getMotd());
		if(val) {
			for(int i = 0; i < spaces; i++) {
				builder.append("\n");
			}
		}
		builder.append("\n"+getMotdUnderline());
		return builder.toString();
	}
	
	public int getMaxPlayerCount() {
		return this.getPropertyInt("max-players");
	}
	
	public boolean isDynamicPlayerCountEnabled() {
		return this.getPropertyBoolean("max-players-plus-1",false);
	}
	
	public int getViewDistance() {
		return this.getPropertyInt("view-distance",10);
	}
	
	public int getDefaultGamemode() {
		return this.getPropertyInt("default-gamemode",0);
	}
	
	public boolean isSpawnProtectionEnabled() {
		return this.getPropertyBoolean("spawn-protection");
	}
	
	public int getSpawnProtectionRadius() {
		return this.getPropertyInt("spawn-protection-radius");
	}
	
	public String getDefaultWorldName() {
		return this.getPropertyString("world-name");
	}
	
	public String getDefaultWorldType() {
		return this.getPropertyString("world-type");
	}
	
	public boolean isNetherAllowed() {
		return this.getPropertyBoolean("allow-nether");
	}
	
	public boolean isEndAllowed() {
		return this.getPropertyBoolean("allow-end");
	}
	
	public boolean isQueryEnabled() {
		return this.getPropertyBoolean("enable-query",true);
	}
	
	public boolean isRconEnabled() {
		return this.getPropertyBoolean("enable-rcon",false);
	}
	
	public boolean isOnlineModeEnabled() {
		return this.getPropertyBoolean("online-mode",true);
	}
	
	
	
	
	public Object getConfig(String variable) {
		return this.getConfig(variable, null);
	}

	public Object getConfig(String variable, Object defaultValue) {
		Object value = this.config.get(variable);
		return value == null ? defaultValue : value;
	}

	public Object getProperty(String variable) {
		return this.getProperty(variable, null);
	}

	public Object getProperty(String variable, Object defaultValue) {
		return this.config.exists(variable) ? this.config.get(variable) : defaultValue;
	}

	public void setPropertyString(String variable, String value) {
		this.config.set(variable, value);
		this.config.save();
	}

	public String getPropertyString(String variable) {
		return this.getPropertyString(variable, null);
	}

	public String getPropertyString(String variable, String defaultValue) {
		return this.config.exists(variable) ? (String) this.config.get(variable) : defaultValue;
	}

	public int getPropertyInt(String variable) {
		return this.getPropertyInt(variable, null);
	}

	public int getPropertyInt(String variable, Integer defaultValue) {
		return this.config.exists(variable) ? (!this.config.get(variable).equals("")
				? Integer.parseInt(String.valueOf(this.config.get(variable)))
				: defaultValue) : defaultValue;
	}

	public void setPropertyInt(String variable, int value) {
		this.config.set(variable, value);
		this.config.save();
	}

	public boolean getPropertyBoolean(String variable) {
		return this.getPropertyBoolean(variable, null);
	}

	public boolean getPropertyBoolean(String variable, Object defaultValue) {
		Object value = this.config.exists(variable) ? this.config.get(variable) : defaultValue;
		if (value instanceof Boolean) {
			return (Boolean) value;
		}
		switch (String.valueOf(value)) {
		case "on":
		case "true":
		case "1":
		case "yes":
			return true;
		}
		return false;
	}

	public void setPropertyBoolean(String variable, boolean value) {
		this.config.set(variable, value ? "1" : "0");
		this.config.save();
	}
	
}