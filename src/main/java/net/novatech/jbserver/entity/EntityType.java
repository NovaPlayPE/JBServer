package net.novatech.jbserver.entity;

public enum EntityType {
	
	HUMAN("minecraft:player", 63),
	COW("minecraft:cow",11);
	
	private String identifier;
	private int id;
	
	EntityType(String identifier, int id){
		this.identifier = identifier;
		this.id = id;
	}
	
	public String getIdentifier() {
		return this.identifier;
	}
	
	public int getIntIdentifier() {
		return this.id;
	}
	
}