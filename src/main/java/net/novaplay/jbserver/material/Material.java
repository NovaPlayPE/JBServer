package net.novaplay.jbserver.material;

public class Material {
	
	private MaterialData data = null;
	
	public Material(MaterialData data) {
		this.data = data;
	}
	
	public MaterialData getData() {
		return this.data;
	}
	
	public MaterialBlock asBlock() {
		return null;
	}
	
	public MaterialItem asItem() {
		return null;
	}
	
}