package net.novaplay.jbserver.entity;

import net.novaplay.library.nbt.tags.CompoundTag;

public class Entity {
	
	private EntityBuilder entityBuilder = null;
	private CompoundTag compoundTag = null;
	
	public Entity(EntityBuilder builder) {
		this.entityBuilder = builder;
	}
		
	public EntityBuilder getEntityBuilder() {
		return this.entityBuilder;
	}
	
}