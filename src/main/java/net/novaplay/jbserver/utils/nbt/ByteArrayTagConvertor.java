package net.novaplay.jbserver.utils.nbt;

import net.novaplay.library.nbt.tags.ByteArrayTag;

public class ByteArrayTagConvertor {
	
	public static ByteArrayTag convertFromJava(com.github.steveice10.opennbt.tag.builtin.ByteArrayTag java) {
		ByteArrayTag tag = new ByteArrayTag(java.getName(), java.getValue());
		return tag;
	}
	
	public static ByteArrayTag convertFromBedrock(com.nukkitx.nbt.tag.ByteArrayTag bedrock) {
		ByteArrayTag tag = new ByteArrayTag(bedrock.getName(), bedrock.getValue());
		return tag;
	}
	
	public static com.github.steveice10.opennbt.tag.builtin.ByteArrayTag convertToJava(ByteArrayTag tag){
		com.github.steveice10.opennbt.tag.builtin.ByteArrayTag java = new com.github.steveice10.opennbt.tag.builtin.ByteArrayTag(tag.getName(), tag.getValue());
		return java;
	}
	
	public static com.nukkitx.nbt.tag.ByteArrayTag convertToBedrock(ByteArrayTag tag){
		com.nukkitx.nbt.tag.ByteArrayTag bedrock = new com.nukkitx.nbt.tag.ByteArrayTag(tag.getName(), tag.getValue());
		return bedrock;
	}
	
}