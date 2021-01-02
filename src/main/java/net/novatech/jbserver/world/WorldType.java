package net.novatech.jbserver.world;

public enum WorldType {
	
	NORMAL("NORMAL",false), //npworld is for minigames and smaller worlds, not survival ones
	FLAT("FLAT",true),
	VOID("VOID",true),
	LARGE_BIOMES("LARGEBIOMES",false),
	AMPLIFIED("AMPLIFIED",false);
	
	private String stringId;
	private boolean npFormat;
	
	WorldType(String stringId, boolean supportsNPFormat){
		this.stringId = stringId;
		this.npFormat = supportsNPFormat;
	}
	
	public String getWorldType() {
		return this.stringId;
	}
	
	public boolean supportsNPFormat() {
		return this.npFormat;
	}
	
	
}