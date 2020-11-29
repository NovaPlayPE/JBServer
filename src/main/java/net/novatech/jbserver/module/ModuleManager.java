package net.novatech.jbserver.module;

import java.util.*;

import lombok.Getter;
import net.novatech.jbserver.entity.ai.AIManager;
import net.novatech.jbserver.server.Server;

public class ModuleManager {
	
	@Getter
	private Server server;
	private HashMap<String, Module> modules = new HashMap<String, Module>();
	
	public static String AI_MODULE = "AIModule";
	
	public ModuleManager(Server server) {
		this.server = server;
		if(server.getModulesSettings().isAIModuleEnabled()) {
			enableModule(AI_MODULE, new AIManager());
		}
	}
	
	public void enableModule(String identifier, Module module) {
		modules.put(identifier, module);
	}
	
	public Module getModule(String identifier){
		return this.modules.get(identifier);
	}
	
	public Set<Map.Entry<String, Module>> getModulesEntry(){
		return this.modules.entrySet();
	}
	
}