package net.novatech.jbserver.utils.nbt;

import net.novatech.library.nbt.tags.IntTag;

public class IntTagConvertor {
	
	public static IntTag convertFromJava(com.github.steveice10.opennbt.tag.builtin.IntTag java) {
		IntTag tag = new IntTag(java.getName(), java.getValue());
		return tag;
	}
	
	public static IntTag convertFromBedrock(com.nukkitx.nbt.tag.IntTag bedrock) {
		IntTag tag = new IntTag(bedrock.getName(), bedrock.getValue());
		return tag;
	}
	
	public static com.github.steveice10.opennbt.tag.builtin.IntTag convertToJava(IntTag tag){
		com.github.steveice10.opennbt.tag.builtin.IntTag java = new com.github.steveice10.opennbt.tag.builtin.IntTag(tag.getName(), tag.getValue());
		return java;
	}
	
	public static com.nukkitx.nbt.tag.IntTag convertToBedrock(IntTag tag){
		com.nukkitx.nbt.tag.IntTag bedrock = new com.nukkitx.nbt.tag.IntTag(tag.getName(), tag.getValue());
		return bedrock;
	}
	
}