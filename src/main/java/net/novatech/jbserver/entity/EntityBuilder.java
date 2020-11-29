package net.novatech.jbserver.entity;

import net.novatech.jbserver.utils.Utils;
import net.novatech.library.math.Rotation;
import net.novatech.library.math.Vector3d;

import net.novatech.library.nbt.tags.*;

public class EntityBuilder {
	
	public CompoundTag namedTag = null;
	
	public Vector3d position;
	public Vector3d motion;
	public Rotation rotation;
	
	public static EntityBuilder readFromNbt(CompoundTag nbt) {
		EntityBuilder builder = new EntityBuilder();
		builder.namedTag = nbt;
		builder.position = new Vector3d(
				(double) ((ListTag)nbt.getValue("Pos")).getValue().get(0).getValue(),
				(double) ((ListTag)nbt.getValue("Pos")).getValue().get(1).getValue(),
				(double) ((ListTag)nbt.getValue("Pos")).getValue().get(2).getValue()
			);
		builder.motion = new Vector3d(
				(double) ((ListTag)nbt.getValue("Motion")).getValue().get(0).getValue(),
				(double) ((ListTag)nbt.getValue("Motion")).getValue().get(1).getValue(),
				(double) ((ListTag)nbt.getValue("Motion")).getValue().get(2).getValue()
			);
		builder.rotation = new Rotation(
				(float) ((ListTag)nbt.getValue("Rotation")).getValue().get(0).getValue(),
				(float) ((ListTag)nbt.getValue("Rotation")).getValue().get(1).getValue()
			);
		return builder;
	}
	
	public static CompoundTag writeToNbt(EntityBuilder builder) {
		return Utils.getDefaultEntityPosition(builder.position, builder.motion, builder.rotation);
	}
	
}