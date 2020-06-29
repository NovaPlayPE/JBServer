package net.novaplay.jbserver.material;

import net.novaplay.library.math.Vector3d;

public enum Material {
	
	AIR(0,0,false,true),
	STONE(1,1,true,true),
	GRASS_BLOCK(2,2,false,true),
	DIRT(3,3,false,true),
	COBBLESTONE(4,4,false,true),
	WOODEN_PLANKS(5,5,true,true),
	SAPPLING(6,6,true,true),
	BEDROCK(7,7,false,true),
	WATER(8,8,false,true),
	WATER_STATIC(9,9,false,true),
	LAVA(10,10,false,true),
	LAVA_STATIC(11,11,false,true),
	SAND(12,12,true,true),
	GRAVEL(13,13,false,true),
	GOLD_ORE(14,14,false,true),
	IRON_ORE(15,15,false,true),
	COAL_ORE(16,16,false,true),
	WOOD(17,17,true,true),
	LEAVES(18,18,true,true),
	SPONGE(19,19,true,true),
	GLASS(20,20,false,true),
	LAPIS_ORE(21,21,false,true),
	LAPIS_BLOCK(22,22,false,true),
	DISPENSER(23,23,false,true),
	SANDSTONE(24,24,true,true),
	NOTE_BLOCK(25,25,false,true),
	BED_BLOCK(26,26,false,true),
	POWERED_RAIL(27,27,false,true),
	BUTTONED_RAIL(28,28,false,true),
	STICKY_PISTON(29,29,false,true),
	COBWEB(30,30,false,true),
	GRASS(31,31,true,true),
	DEADBUSH(32,32,false,true),
	PISTON(33,33,false,true),
	PISTON_SEPARATED(34,34,false,true),
	WOOL(35,35,true,true),
	FLOWER(37,37,false,true),
	FLOWER_COLORED(38,38,true,true),
	BROWN_MUSHROOM(39,39,false,true),
	RED_MUSHROOM(40,40,false,true),
	GOLD_BLOCK(41,41,false,true),
	IRON_BLOCK(42,42,false,true),
	SMOOTH_BLOCK(43,43,true,true),
	SLAB(44,44,true,true),
	BRICKS(45,45,false,true),
	TNT(46,46,false,true),
	BOOKSHELF(47,47,false,true),
	MOSSY_COBBLESTOONE(48,48,false,true),
	OBSIDIAN(49,49,false,true),
	TORCH(50,50,false,true);
	
	Material(int javaId, int bedrockId, boolean multipleData, boolean isBlock){
		this.javaId = javaId;
		this.bedrockId = bedrockId;
		this.multipleData = multipleData;
		this.isBlock = isBlock;
	}
	
	private int javaId;
	private int bedrockId;
	private boolean multipleData = false;
	private boolean isBlock;
	
	public int getJavaId() {
		return this.javaId;
	}
	
	public int getBedrockId() {
		return this.bedrockId;
	}
	
	public boolean hasMultipleData() {
		return this.multipleData;
	}
	
	public boolean isBlock() {
		return this.isBlock;
	}
	
	public MaterialBlock toBlock(Vector3d vector) throws Exception{
		return toBlock(vector,null);
	}
	
	public MaterialBlock toBlock(Vector3d vector, MaterialData data) throws Exception {
		if(this.isBlock()) {
			return new MaterialBlock(this, vector, data);
		}
		throw new Exception("This material is not a block");
	}
	
	public MaterialItem toItem() {
		return toItem(0);
	}
	
	public MaterialItem toItem(int count) {
		return toItem(count,null);
	}
	
	public MaterialItem toItem(int count, MaterialData data) {
		return new MaterialItem(this,count,data);
	}
	
}