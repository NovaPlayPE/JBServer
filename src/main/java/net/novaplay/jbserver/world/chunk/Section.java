package net.novaplay.jbserver.world.chunk;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.IntList;
import io.netty.buffer.ByteBuf;

import net.novaplay.jbserver.utils.NibbleArray;

import net.novaplay.library.nbt.tags.*;

public class Section {
	
	private static int SIZE = 4096;
	
	private IntList pallette;
	
	
	private short[][] blocks = null;
	private NibbleArray blockLight = null;
	private NibbleArray skyLight = null;
	
	public Section() {
		this(new short[2][SIZE], new NibbleArray(SIZE), new NibbleArray(SIZE));
	}
	
	private Section(short[][] blocks, NibbleArray blockLight, NibbleArray skyLight) { //multi palettes not implemented yet...
		this.blocks = blocks;
		this.blockLight = blockLight;
		this.skyLight = skyLight;
	}
	
	public void setBlockLight(int x, int y,  int z, byte value) {
		this.blockLight.set(x, y, z, value);
	}
	
	public byte getBlockLight(int x, int y, int z) {
		return this.blockLight.get(x, y, z);
	}
	
	public void setSkyLight(int x, int y, int z, byte value) {
		this.skyLight.set(x, y, z, value);
	}
	
	public byte getSkyLight(int x, int y, int z) {
		return this.skyLight.get(x, y, x);
	}
	
	public void writeToJava(ByteBuf buf) {
		buf.writeByte(13);
	}
	
	public void writeToBedrock(ByteBuf buf) {
		
	}
	
	public void save(CompoundTag nbt) {
	}
	
}