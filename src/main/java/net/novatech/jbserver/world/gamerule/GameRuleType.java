package net.novatech.jbserver.world.gamerule;

import net.novatech.jbserver.world.WorldException;

public enum GameRuleType{
	
	DO_DAYLIGHT_CYCLE("dodaylightcycle", Boolean.class);
	
	GameRuleType(String id, Class<? extends Object> clazz){
		this.id = id;
		this.clazz = clazz;
	}
	
	private String id;
	private Class<? extends Object> clazz;
	
	public String getId() {
		return this.id;
	}
	
	public Class<? extends Object> getClazz(){
		return this.clazz;
	}
	
	public static GameRuleType findById(String id) {
		for(GameRuleType type : GameRuleType.values()) {
			if(type.getId().equalsIgnoreCase(id)) {
				return type;
			}
		}
		throw new WorldException("Such gamerule by type " + id + " not found");
	}
	
	public boolean validateValue(Object object) {
		if(object instanceof Integer || object instanceof Boolean) {
			return true;
		}
		throw new WorldException("Value is neither Boolean or Integer");
	}
	
}