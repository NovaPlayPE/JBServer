package net.novatech.jbserver.world.chunk;

import com.google.common.base.Preconditions;
//import it.unimi.dsi.fastutil.ints.IntList;
import io.netty.buffer.ByteBuf;
import net.novatech.jbserver.utils.NibbleArray;
import net.novatech.library.nbt.tags.*;

public class ChunkSection {
	
	private static int SIZE = 4096;
	
	//private IntList pallette;
	
	
	private ByteBuf[] blocks = new ByteBuf[2];
	private NibbleArray blockLight = null;
	private NibbleArray skyLight = null;
	
	public ChunkSection() {
		this(new ByteBuf[2], new NibbleArray(SIZE), new NibbleArray(SIZE));
	}
	
	private ChunkSection(ByteBuf[] blocks, NibbleArray blockLight, NibbleArray skyLight) { //multi palettes not implemented yet...
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
	
	public short getRuntimeId(int x, int y, int z, int layer) {
		return getRuntimeId((y << 8 | z << 4 | x), layer);
	}
	
	public short getRuntimeId(int index, int layer) {
		ByteBuf storage = this.blocks[layer];
		return storage.getShort(index << 1);
	}
	
	public void writeToJavaNetwork(ByteBuf buf) {
		
	}
	
	public void writeToBedrockNetwork(ByteBuf buf) {
		buf.writeByte(8);
		
	}
	
	public void save(CompoundTag nbt) {
		
	}
	
}