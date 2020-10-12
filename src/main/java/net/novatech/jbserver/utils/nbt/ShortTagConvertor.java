package net.novatech.jbserver.utils.nbt;

import net.novatech.library.nbt.tags.ShortTag;

public class ShortTagConvertor {
	
	public static ShortTag convertFromJava(com.github.steveice10.opennbt.tag.builtin.ShortTag java) {
		ShortTag tag = new ShortTag(java.getName(), java.getValue());
		return tag;
	}
	
	public static ShortTag convertFromBedrock(com.nukkitx.nbt.tag.ShortTag bedrock) {
		ShortTag tag = new ShortTag(bedrock.getName(), bedrock.getValue());
		return tag;
	}
	
	public static com.github.steveice10.opennbt.tag.builtin.ShortTag convertToJava(ShortTag tag){
		com.github.steveice10.opennbt.tag.builtin.ShortTag java = new com.github.steveice10.opennbt.tag.builtin.ShortTag(tag.getName(), tag.getValue());
		return java;
	}
	
	public static com.nukkitx.nbt.tag.ShortTag convertToBedrock(ShortTag tag){
		com.nukkitx.nbt.tag.ShortTag bedrock = new com.nukkitx.nbt.tag.ShortTag(tag.getName(), tag.getValue());
		return bedrock;
	}
	
}