package net.novatech.jbserver.world;

import net.novatech.jbserver.material.MaterialBlock;
import net.novatech.library.math.Rotation;
import net.novatech.library.math.Vector3d;
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
	private Rotation rotation = null;
	
	public Location(World world, Vector3d vector) {
		this(world, vector, new Rotation(0,0));
	}
	
	public Location(World world, Vector3d vector, Rotation rotation) {
		this.world = world;
		this.vector = vector;
		this.rotation = rotation;
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
	
	public float getYaw() {
		return this.rotation.getYaw();
	}
	
	public float getPitch() {
		return this.rotation.getPitch();
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
	
	public void setYaw(float yaw) {
		this.rotation.setYaw(yaw);
	}
	
	public void setPitch(float pitch) {
		this.rotation.setPitch(pitch);
	}
	
	/**
	 * it's not ready, yet
	 */
	public MaterialBlock getBlock() {
		return this.getWorld().getBlock(this.getX(), this.getY(), this.getZ());
	}
}