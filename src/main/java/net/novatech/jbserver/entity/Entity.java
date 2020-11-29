package net.novatech.jbserver.entity;

import net.novatech.jbserver.world.Location;
import net.novatech.library.nbt.tags.CompoundTag;

import lombok.Getter;
import lombok.Setter;

public class Entity {
	
	private EntityBuilder entityBuilder = null;
	private CompoundTag compoundTag = null;
	
	@Getter
	@Setter
	private Location location = null;
	
	public Entity(EntityBuilder builder) {
		this.entityBuilder = builder;
		init();
	}
		
	public EntityBuilder getEntityBuilder() {
		return this.entityBuilder;
	}
	
	public void init() {
		this.location = new Location(getEntityBuilder().getWorld(), getEntityBuilder().getPositionVector(), getEntityBuilder().getRotation());
	}
	
	public void save() {
		
	}
	
}