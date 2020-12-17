package net.novatech.jbserver.entity.human;

import net.novatech.jbserver.entity.EntityLiving;
import net.novatech.jbserver.entity.EntityType;

public class EntityHumanoid extends EntityLiving {
	
	private Skin skin;

	public EntityHumanoid(EntityType type) {
		super(type);
	}
	
	public Skin getSkin() {
		return this.skin;
	}
	
	public void setSkin(Skin skin) {
		this.skin = skin;
	}

}
