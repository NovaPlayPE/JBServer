package net.novaplay.jbserver.utils.nbt;

import net.novaplay.library.nbt.tags.FloatTag;

public class FloatTagConvertor {
	
	public static FloatTag convertFromJava(com.github.steveice10.opennbt.tag.builtin.FloatTag java) {
		FloatTag tag = new FloatTag(java.getName(), java.getValue());
		return tag;
	}
	
	public static FloatTag convertFromBedrock(com.nukkitx.nbt.tag.FloatTag bedrock) {
		FloatTag tag = new FloatTag(bedrock.getName(), bedrock.getValue());
		return tag;
	}
	
	public static com.github.steveice10.opennbt.tag.builtin.FloatTag convertToJava(FloatTag tag){
		com.github.steveice10.opennbt.tag.builtin.FloatTag java = new com.github.steveice10.opennbt.tag.builtin.FloatTag(tag.getName(), tag.getValue());
		return java;
	}
	
	public static com.nukkitx.nbt.tag.FloatTag convertToBedrock(FloatTag tag){
		com.nukkitx.nbt.tag.FloatTag bedrock = new com.nukkitx.nbt.tag.FloatTag(tag.getName(), tag.getValue());
		return bedrock;
	}
	
}