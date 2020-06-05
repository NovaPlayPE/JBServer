package net.novaplay.jbserver.utils;

import java.util.Arrays;

public class NibbleArray {
	
	private int size;
	private byte[] data = null;
	
	public NibbleArray(int size) {
		this.size = size;
		this.data = new byte[size >> 1];
	}
	
	public NibbleArray(byte[] bytes) {
		this.size = bytes.length << 1;
		this.data = bytes;
	}
	
	public void setData(byte[] data) {
		System.arraycopy(data, 0, this.data, 0, data.length);
	}
	
	public byte[] getData() {
		return this.data;
	}
	
	public void set(int x, int y, int z, byte value) {
		int key = y << 8 | z << 4 | x;
		set(key,value);
	}
	
	public void set(int key, byte value) {
		int index = key >> 1;
		int part = key & 1;
		if(part == 0) {
			this.data[index] = (byte) (this.data[index] & 0xf0 | value);
		} else {
			this.data[index] = (byte) (this.data[index] & 0x0f | value << 4);
		}
	}
	
	public byte get(int x, int y, int z) {
		int key = y << 8 | z << 4 | x;
		return get(key);
	}
	
	public byte get(int key) {
		int index = key >> 1;
		int part = key & 1;
		return part == 0 ? (byte) (this.data[index] & 0x0f) : (byte) ((this.data[index] & 0xf0) >>> 4);
	}
	
	public void fill(byte value) {
		Arrays.fill(this.data, (byte) ((value << 4) | value));
	}
	
	public void copyFrom(NibbleArray array) {
		copyFrom(array.data);
	}
	
	public void copyFrom(byte[] bytes) {
		System.arraycopy(bytes,0,this.data,0,this.data.length);
	}
	
}