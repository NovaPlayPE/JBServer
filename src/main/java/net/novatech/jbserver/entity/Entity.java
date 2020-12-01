package net.novatech.jbserver.entity;

import net.novatech.jbserver.player.Player;
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
	
	@Getter
	private EntityBuilder entityBuilder = null;
	
	public Entity(EntityType type) {
		this.entityType = type;
	}
		
	public EntityType getEntityType() {
		return this.entityType;
	}
	
	public void spawnEntity() {
		
	}
	
	public void spawnEntity(Player viewer) {
		
	}
	
	public void init(EntityBuilder builder) {
		this.entityBuilder = builder;
	}
	
	public void save() {
		
	}
	
}