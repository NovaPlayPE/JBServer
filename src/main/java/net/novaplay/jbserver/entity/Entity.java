package net.novaplay.jbserver.entity;

public class Entity {
	
	private EntityBuilder entityBuilder = null;
	private com.github.steveice10.opennbt.tag.builtin.CompoundTag javaNbt = null;
	private com.nukkitx.nbt.tag.CompoundTag bedrockNbt = null;
	
	public Entity(EntityBuilder builder) {
		this.entityBuilder = builder;
	}
		
	public EntityBuilder getEntityBuilder() {
		return this.entityBuilder;
	}
	
}