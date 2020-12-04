package net.novatech.jbserver.entity;

import lombok.NonNull;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.utils.Utils;
import net.novatech.jbserver.world.World;
import net.novatech.jbserver.world.chunk.Chunk;
import net.novatech.library.math.Rotation;
import net.novatech.library.math.Vector3d;

import net.novatech.library.nbt.tags.*;

public class EntityBuilder {
	
	public CompoundTag namedTag = null;
	
	private Vector3d position = null;
	private Vector3d motion = null;
	private Rotation rotation = null;
	private World world = null;
	
	public void addPositionVector(Vector3d pos) {
		this.position = pos;
	}
	
	public void addMotionVector(Vector3d pos) {
		this.motion = pos;
	}
	
	public void addRotation(Rotation rot) {
		this.rotation = rot;
	}
	
	public void setWorld(World w) {
		this.world = w;
	}
	
	public Vector3d getPositionVector() {
		return this.position;
	}
	
	public Vector3d getMotionVector() {
		return this.motion;
	}
	
	public Rotation getRotation() {
		return this.rotation;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public Chunk getChunk() {
		return this.world.getChunkManager().getChunk(getPositionVector().asInt().getX() >> 4, getPositionVector().asInt().getZ() >> 4);
	}
	
	public static EntityBuilder newEntity(@NonNull Vector3d posVector) {
		return newEntity(posVector, new Vector3d(0,0,0));
	}
	
	public static EntityBuilder newEntity(@NonNull Vector3d pos, Vector3d motion) {
		return newEntity(pos, motion, new Rotation(0,0));
	}
	
	public static EntityBuilder newEntity(@NonNull Vector3d pos, Vector3d motion, Rotation rot) {
		return newEntity(pos, motion, rot, Server.getInstance().getFactoryManager().getWorldFactory().getDefaultWorld());
	}
	
	/**
	 * This method is used, when we are creating new entity, builder class will throw default nbt values
	 * @param pos
	 * @param motion
	 * @param rot
	 * @param world
	 * @return
	 */
	public static EntityBuilder newEntity(@NonNull Vector3d pos, Vector3d motion, Rotation rot, World world) {
		EntityBuilder builder = new EntityBuilder();
		
		CompoundTag nbt = Utils.getDefaultEntityPosition(pos, motion, rot);
		builder.namedTag = nbt;
		builder.addPositionVector(pos);
		builder.addMotionVector(motion);
		builder.addRotation(rot);
		builder.setWorld(world);
		
		return builder;
	}
	
	
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