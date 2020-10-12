package net.novatech.jbserver.entity;

import net.novatech.library.nbt.tags.CompoundTag;

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