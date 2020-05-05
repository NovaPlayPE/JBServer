package net.novaplay.jbserver.world.chunk;

import net.novaplay.jbserver.utils.world.NibbleArray;

public class Section {
	
	private NibbleArray blockLight = null;
	private NibbleArray skyLight = null;
	
	public Section(byte[] blockLight, byte[] skyLight) { //multi palettes not implemented yet...
		this.blockLight = new NibbleArray(blockLight);
		this.skyLight = new NibbleArray(skyLight);
	}
	
}