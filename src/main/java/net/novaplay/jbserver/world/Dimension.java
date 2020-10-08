package net.novaplay.jbserver.world;

import lombok.Getter;

public enum Dimension {
	
	OVERWORLD("minecraft:overworld"),
	NETHER("minecraft:nether"),
	END("minecraft:end");
	
	@Getter
	private String identifier;
	
	Dimension(String identifier){
		this.identifier = identifier;
	}
	
}