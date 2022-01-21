package net.novatech.jbserver.server.settings;

import net.novatech.jbserver.config.Config;

public class ServerSettings extends BaseSettings{
	
	public ServerSettings(Config config) {
		super(config);
	}
	
	public String getAddress() {
		return this.getPropertyString("server-ip", "0.0.0.0");
	}
	
	public int getJavaPort() {
		return this.getPropertyInt("java-port",25565);
	}
	
	public int getBedrockPort() {
		return this.getPropertyInt("bedrock-port",19132);
	}
	
	public boolean javaServerEnabled() {
		return this.getPropertyBoolean("java-enabled", true);
	}
	
	public boolean bedrockServerEnabled() {
		return this.getPropertyBoolean("bedrock-enabled", true);
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
	
	public String getDefaultGamemodeString() {
		return this.getDefaultGamemodeString(false);
	}
	
	public String getDefaultGamemodeString(boolean direct) {
		int gm = getDefaultGamemode();
		
		return switch(gm) {
		case 0 -> direct ? "Survival" : "%gameMode.survival";
		case 1 -> direct ? "Creative" : "%gameMode.creative";
		case 2 -> direct ? "Adventure" : "%gameMode.adventure";
		case 3 -> direct ? "Spectator" : "%gameMode.spectator";
		default -> "UNKNOWN";
		};
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
	
	public long getDefaultWorldSeed() {
		return (long) this.getPropertyInt("world-seed");
	}
	
	public boolean useInMemoryWorlds() {
		return this.getPropertyBoolean("inmemory-worlds", true);
	}
	
	public String getDefaultWorldType() {
		return this.getPropertyString("world-type");
	}
	
	public String getDefaultWorldProvider() {
		return this.getPropertyString("world-provider");
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
	
	public boolean isTimingsEnabled() {
		return this.getPropertyBoolean("timings-enabled", false);
	}
	
	public boolean isTimingsVerbose() {
		return this.getPropertyBoolean("timings-verbose", false);
	}
	
	public int getTimingsInterval() {
		return this.getPropertyInt("timings-history-interval", 6000);
	}
	
	public int getTimingsLength() {
		return this.getPropertyInt("timings-history-length", 72000);
	}
	
}