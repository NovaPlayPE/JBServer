package net.novaplay.jbserver.material;

import net.novaplay.library.math.Vector3d;

public class MaterialBlock {
	
	private Material material;
	private Vector3d vector;
	private MaterialData data;
	
	public MaterialBlock(Material material, Vector3d vector, MaterialData data) {
		this.material = material;
		this.vector = vector;
		this.data = data;
	}
}