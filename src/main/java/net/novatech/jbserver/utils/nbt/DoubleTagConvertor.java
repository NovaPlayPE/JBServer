package net.novatech.jbserver.utils.nbt;

import net.novatech.library.nbt.tags.DoubleTag;

public class DoubleTagConvertor {
	
	public static DoubleTag convertFromJava(com.github.steveice10.opennbt.tag.builtin.DoubleTag java) {
		DoubleTag tag = new DoubleTag(java.getName(), java.getValue());
		return tag;
	}
	
	public static DoubleTag convertFromBedrock(com.nukkitx.nbt.tag.DoubleTag bedrock) {
		DoubleTag tag = new DoubleTag(bedrock.getName(), bedrock.getValue());
		return tag;
	}
	
	public static com.github.steveice10.opennbt.tag.builtin.DoubleTag convertToJava(DoubleTag tag){
		com.github.steveice10.opennbt.tag.builtin.DoubleTag java = new com.github.steveice10.opennbt.tag.builtin.DoubleTag(tag.getName(), tag.getValue());
		return java;
	}
	
	public static com.nukkitx.nbt.tag.DoubleTag convertToBedrock(DoubleTag tag){
		com.nukkitx.nbt.tag.DoubleTag bedrock = new com.nukkitx.nbt.tag.DoubleTag(tag.getName(), tag.getValue());
		return bedrock;
	}
	
}