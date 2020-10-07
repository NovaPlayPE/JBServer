package net.novaplay.jbserver.utils.nbt;

import net.novaplay.library.nbt.tags.LongTag;

public class LongTagConvertor {
	
	public static LongTag convertFromJava(com.github.steveice10.opennbt.tag.builtin.LongTag java) {
		LongTag tag = new LongTag(java.getName(), java.getValue());
		return tag;
	}
	
	public static LongTag convertFromBedrock(com.nukkitx.nbt.tag.LongTag bedrock) {
		LongTag tag = new LongTag(bedrock.getName(), bedrock.getValue());
		return tag;
	}
	
	public static com.github.steveice10.opennbt.tag.builtin.LongTag convertToJava(LongTag tag){
		com.github.steveice10.opennbt.tag.builtin.LongTag java = new com.github.steveice10.opennbt.tag.builtin.LongTag(tag.getName(), tag.getValue());
		return java;
	}
	
	public static com.nukkitx.nbt.tag.LongTag convertToBedrock(LongTag tag){
		com.nukkitx.nbt.tag.LongTag bedrock = new com.nukkitx.nbt.tag.LongTag(tag.getName(), tag.getValue());
		return bedrock;
	}
	
}