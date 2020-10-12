package net.novatech.jbserver.utils.nbt;

import net.novatech.library.nbt.tags.ByteTag;

public class ByteTagConvertor {
	
	public static ByteTag convertFromJava(com.github.steveice10.opennbt.tag.builtin.ByteTag java) {
		ByteTag tag = new ByteTag(java.getName(), java.getValue());
		return tag;
	}
	
	public static ByteTag convertFromBedrock(com.nukkitx.nbt.tag.ByteTag bedrock) {
		ByteTag tag = new ByteTag(bedrock.getName(), bedrock.getValue());
		return tag;
	}
	
	public static com.github.steveice10.opennbt.tag.builtin.ByteTag convertToJava(ByteTag tag){
		com.github.steveice10.opennbt.tag.builtin.ByteTag java = new com.github.steveice10.opennbt.tag.builtin.ByteTag(tag.getName(), tag.getValue());
		return java;
	}
	
	public static com.nukkitx.nbt.tag.ByteTag convertToBedrock(ByteTag tag){
		com.nukkitx.nbt.tag.ByteTag bedrock = new com.nukkitx.nbt.tag.ByteTag(tag.getName(), tag.getValue());
		return bedrock;
	}
	
}