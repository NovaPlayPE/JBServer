package net.novaplay.jbserver.world;

import net.novaplay.library.math.Vector3d;

import net.novaplay.jbserver.material.MaterialBlock;

import lombok.Getter;
import lombok.Setter;

public class Location {
	
	@Getter
	@Setter
	private World world = null;
	@Getter
	@Setter
	private Vector3d vector = null;
	@Getter
	@Setter
	private float yaw;
	@Getter
	@Setter
	private float pitch;
	
	public Location(World world, Vector3d vector) {
		this(world, vector, 0, 0);
	}
	
	public Location(World world, Vector3d vector, float yaw, float pitch) {
		this.world = world;
		this.vector = vector;
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	public double getX() {
		return this.vector.getX();
	}
	
	public double getY() {
		return this.vector.getY();
	}
	
	public double getZ() {
		return this.vector.getZ();
	}
	
	public void setX(double x) {
		this.vector.setX(x);
	}
	
	public void setY(double y) {
		this.vector.setY(y);
	}
	
	public void setZ(double z) {
		this.vector.setZ(z);
	}
	
	/**
	 * it's not ready, yet
	 */
	public MaterialBlock getBlock() {
		return null;
	}
}