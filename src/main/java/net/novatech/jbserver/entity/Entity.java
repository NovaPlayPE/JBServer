package net.novatech.jbserver.entity;

import net.novatech.jbserver.world.Location;
import net.novatech.library.nbt.tags.CompoundTag;

import lombok.Getter;
import lombok.Setter;

public abstract class Entity implements IEntity{
	
	private EntityType entityType = null;
	private CompoundTag compoundTag = null;
	
	@Getter
	@Setter
	private Location location = null;
	
	public Entity(EntityType type) {
		this.entityType = type;
		init();
	}
		
	public EntityType getEntityType() {
		return this.entityType;
	}
	
	public void init() {
		
	}
	
	public void save() {
		
	}
	
}