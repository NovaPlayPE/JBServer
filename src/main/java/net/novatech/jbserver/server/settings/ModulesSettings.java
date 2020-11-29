package net.novatech.jbserver.server.settings;

import net.novatech.jbserver.config.Config;

public class ModulesSettings extends BaseSettings {

	public ModulesSettings(Config config) {
		super(config);
	}
	
	public boolean isAIModuleEnabled() {
		return this.getPropertyBoolean("ai-module");
	}
	

}
