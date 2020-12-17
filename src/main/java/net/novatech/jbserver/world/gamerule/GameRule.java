package net.novatech.jbserver.world.gamerule;

public abstract class GameRule{
	
	private GameRuleType gamerule;
	protected Object value;
	
	public GameRule(GameRuleType type, Object value) {
		this.gamerule = type;
		this.value = value;
	}
	
	public GameRuleType getInitialData() {
		return this.gamerule;
	}
	
	public abstract Object getValue();
	public abstract void setValue(Object object);
	
}