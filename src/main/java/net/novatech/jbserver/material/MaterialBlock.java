package net.novatech.jbserver.material;

import lombok.Getter;
import net.novatech.library.math.Vector3i;

public class MaterialBlock {
	
	@Getter
	private Material material;
	@Getter
	private Vector3i vector;
	private MaterialData data;
	
	public MaterialBlock(Material material, Vector3i vector, MaterialData data) {
		this.material = material;
		this.vector = vector;
		this.data = data;
	}
}