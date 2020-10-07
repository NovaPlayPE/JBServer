package net.novaplay.jbserver.utils.nbt;

import net.novaplay.library.nbt.tags.StringTag;

public class StringTagConvertor {
	
	public static StringTag convertFromJava(com.github.steveice10.opennbt.tag.builtin.StringTag java) {
		StringTag tag = new StringTag(java.getName(), java.getValue());
		return tag;
	}
	
	public static StringTag convertFromBedrock(com.nukkitx.nbt.tag.StringTag bedrock) {
		StringTag tag = new StringTag(bedrock.getName(), bedrock.getValue());
		return tag;
	}
	
	public static com.github.steveice10.opennbt.tag.builtin.StringTag convertToJava(StringTag tag){
		com.github.steveice10.opennbt.tag.builtin.StringTag java = new com.github.steveice10.opennbt.tag.builtin.StringTag(tag.getName(), tag.getValue());
		return java;
	}
	
	public static com.nukkitx.nbt.tag.StringTag convertToBedrock(StringTag tag){
		com.nukkitx.nbt.tag.StringTag bedrock = new com.nukkitx.nbt.tag.StringTag(tag.getName(), tag.getValue());
		return bedrock;
	}
	
}