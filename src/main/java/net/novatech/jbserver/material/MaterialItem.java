package net.novatech.jbserver.material;

public class MaterialItem {
	
	private Material material;
	private int count;
	private MaterialData data;
	
	public MaterialItem(Material material, int count, MaterialData data) {
		this.material = material;
		this.count = count;
		this.data = data;
	}
	
}