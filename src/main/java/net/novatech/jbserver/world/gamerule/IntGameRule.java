package net.novatech.jbserver.world.gamerule;

public class IntGameRule extends GameRule {

	public IntGameRule(GameRuleType type, Integer value) {
		super(type, value);
	}

	@Override
	public Integer getValue() {
		return (Integer)this.value;
	}

	@Override
	public void setValue(Object object) {
		if(getInitialData().validateValue(object)) {
			this.value = (Integer)object;
		}
	}

}