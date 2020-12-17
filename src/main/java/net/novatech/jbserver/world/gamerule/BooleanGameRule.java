package net.novatech.jbserver.world.gamerule;

public class BooleanGameRule extends GameRule {

	public BooleanGameRule(GameRuleType type, Boolean value) {
		super(type, value);
	}

	@Override
	public Boolean getValue() {
		return (Boolean)this.value;
	}

	@Override
	public void setValue(Object object) {
		if(getInitialData().validateValue(object)) {
			this.value = (Boolean)object;
		}
	}

}
