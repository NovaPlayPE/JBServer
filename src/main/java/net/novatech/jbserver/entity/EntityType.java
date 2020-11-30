package net.novatech.jbserver.entity;

public enum EntityType {
	
	HUMAN("minecraft:player"),
	COW("minecraft:cow");
	
	private String identifier;
	
	EntityType(String identifier){
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return this.identifier;
	}
	
}