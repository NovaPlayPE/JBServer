package net.novatech.jbserver.utils.nbt;

import java.util.*;

import net.novatech.library.nbt.tags.*;

public class ListTagConvertor {
	
	public static ListTag convertFromJava(com.github.steveice10.opennbt.tag.builtin.ListTag java) {
		ArrayList<Tag> value = new ArrayList<Tag>();
		List<com.github.steveice10.opennbt.tag.builtin.Tag> javaValues = java.getValue();
		for(com.github.steveice10.opennbt.tag.builtin.Tag javaTags : javaValues) {
			if(javaTags instanceof com.github.steveice10.opennbt.tag.builtin.ByteTag) {
				value.add(ByteTagConvertor.convertFromJava((com.github.steveice10.opennbt.tag.builtin.ByteTag)javaTags));
			}if(javaTags instanceof com.github.steveice10.opennbt.tag.builtin.ShortTag) {
				value.add(ShortTagConvertor.convertFromJava((com.github.steveice10.opennbt.tag.builtin.ShortTag)javaTags));
			}if(javaTags instanceof com.github.steveice10.opennbt.tag.builtin.IntTag) {
				value.add(IntTagConvertor.convertFromJava((com.github.steveice10.opennbt.tag.builtin.IntTag)javaTags));
			}if(javaTags instanceof com.github.steveice10.opennbt.tag.builtin.LongTag) {
				value.add(LongTagConvertor.convertFromJava((com.github.steveice10.opennbt.tag.builtin.LongTag)javaTags));
			}if(javaTags instanceof com.github.steveice10.opennbt.tag.builtin.FloatTag) {
				value.add(FloatTagConvertor.convertFromJava((com.github.steveice10.opennbt.tag.builtin.FloatTag)javaTags));
			}if(javaTags instanceof com.github.steveice10.opennbt.tag.builtin.DoubleTag) {
				value.add(DoubleTagConvertor.convertFromJava((com.github.steveice10.opennbt.tag.builtin.DoubleTag)javaTags));
			}if(javaTags instanceof com.github.steveice10.opennbt.tag.builtin.ByteArrayTag) {
				value.add(ByteArrayTagConvertor.convertFromJava((com.github.steveice10.opennbt.tag.builtin.ByteArrayTag)javaTags));
			}if(javaTags instanceof com.github.steveice10.opennbt.tag.builtin.StringTag) {
				value.add(StringTagConvertor.convertFromJava((com.github.steveice10.opennbt.tag.builtin.StringTag)javaTags));
			}
		}
		
		ListTag tag = new ListTag(java.getName(), value);
		return tag;
	}
	
	public static ListTag convertFromBedrock(com.nukkitx.nbt.tag.ListTag bedrock) {
		ArrayList<Tag> value = new ArrayList<Tag>();
		List<com.nukkitx.nbt.tag.Tag> bedrockValues = bedrock.getValue();
		for(com.nukkitx.nbt.tag.Tag bedrockTags : bedrockValues) {
			if(bedrockTags instanceof com.nukkitx.nbt.tag.ByteTag) {
				value.add(ByteTagConvertor.convertFromBedrock((com.nukkitx.nbt.tag.ByteTag)bedrockTags));
			}if(bedrockTags instanceof com.nukkitx.nbt.tag.ShortTag) {
				value.add(ShortTagConvertor.convertFromBedrock((com.nukkitx.nbt.tag.ShortTag)bedrockTags));
			}if(bedrockTags instanceof com.nukkitx.nbt.tag.IntTag) {
				value.add(IntTagConvertor.convertFromBedrock((com.nukkitx.nbt.tag.IntTag)bedrockTags));
			}if(bedrockTags instanceof com.nukkitx.nbt.tag.LongTag) {
				value.add(LongTagConvertor.convertFromBedrock((com.nukkitx.nbt.tag.LongTag)bedrockTags));
			}if(bedrockTags instanceof com.nukkitx.nbt.tag.FloatTag) {
				value.add(FloatTagConvertor.convertFromBedrock((com.nukkitx.nbt.tag.FloatTag)bedrockTags));
			}if(bedrockTags instanceof com.nukkitx.nbt.tag.DoubleTag) {
				value.add(DoubleTagConvertor.convertFromBedrock((com.nukkitx.nbt.tag.DoubleTag)bedrockTags));
			}if(bedrockTags instanceof com.nukkitx.nbt.tag.ByteArrayTag) {
				value.add(ByteArrayTagConvertor.convertFromBedrock((com.nukkitx.nbt.tag.ByteArrayTag)bedrockTags));
			}if(bedrockTags instanceof com.nukkitx.nbt.tag.StringTag) {
				value.add(StringTagConvertor.convertFromBedrock((com.nukkitx.nbt.tag.StringTag)bedrockTags));
			}
		}
		
		ListTag tag = new ListTag(bedrock.getName(), value);
		return tag;
	}
	
	public static com.github.steveice10.opennbt.tag.builtin.ListTag convertToJava(ListTag tag){
		List<com.github.steveice10.opennbt.tag.builtin.Tag> value = new ArrayList<com.github.steveice10.opennbt.tag.builtin.Tag>();
		List<Tag> values = tag.getValue();
		for(Tag tags : values) {
			if(tags instanceof ByteTag) {
				value.add(ByteTagConvertor.convertToJava((ByteTag)tags));
			}else if(tags instanceof ShortTag) {
				value.add(ShortTagConvertor.convertToJava((ShortTag)tags));
			}else if(tags instanceof IntTag) {
				value.add(IntTagConvertor.convertToJava((IntTag)tags));
			}else if(tags instanceof LongTag) {
				value.add(LongTagConvertor.convertToJava((LongTag)tags));
			}else if(tags instanceof FloatTag) {
				value.add(FloatTagConvertor.convertToJava((FloatTag)tags));
			}else if(tags instanceof DoubleTag) {
				value.add(DoubleTagConvertor.convertToJava((DoubleTag)tags));
			}else if(tags instanceof ByteArrayTag) {
				value.add(ByteArrayTagConvertor.convertToJava((ByteArrayTag)tags));
			}else if(tags instanceof StringTag) {
				value.add(StringTagConvertor.convertToJava((StringTag)tags));
			}
		}
		
		com.github.steveice10.opennbt.tag.builtin.ListTag java = new com.github.steveice10.opennbt.tag.builtin.ListTag(tag.getName(), value);
		return java;
	}
	
	public static com.nukkitx.nbt.tag.ListTag convertToBedrock(ListTag tag){
		List<com.nukkitx.nbt.tag.Tag> value = new ArrayList<com.nukkitx.nbt.tag.Tag>();
		List<Tag> values = tag.getValue();
		for(Tag tags : values) {
			if(tags instanceof ByteTag) {
				value.add(ByteTagConvertor.convertToBedrock((ByteTag)tags));
			}else if(tags instanceof ShortTag) {
				value.add(ShortTagConvertor.convertToBedrock((ShortTag)tags));
			}else if(tags instanceof IntTag) {
				value.add(IntTagConvertor.convertToBedrock((IntTag)tags));
			}else if(tags instanceof LongTag) {
				value.add(LongTagConvertor.convertToBedrock((LongTag)tags));
			}else if(tags instanceof FloatTag) {
				value.add(FloatTagConvertor.convertToBedrock((FloatTag)tags));
			}else if(tags instanceof DoubleTag) {
				value.add(DoubleTagConvertor.convertToBedrock((DoubleTag)tags));
			}else if(tags instanceof ByteArrayTag) {
				value.add(ByteArrayTagConvertor.convertToBedrock((ByteArrayTag)tags));
			}else if(tags instanceof StringTag) {
				value.add(StringTagConvertor.convertToBedrock((StringTag)tags));
			}
		}
		
		com.nukkitx.nbt.tag.ListTag bedrock = new com.nukkitx.nbt.tag.ListTag(tag.getName(), value.get(0).getClass(), value);
		return bedrock;
	}
	
}