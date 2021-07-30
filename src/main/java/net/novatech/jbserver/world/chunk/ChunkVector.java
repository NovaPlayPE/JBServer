package net.novatech.jbserver.world.chunk;

import lombok.NonNull;
import lombok.Getter;
import lombok.Setter;

import net.novatech.library.math.*;

public class ChunkVector{
	
	@Getter
	@Setter
	public int x;
	@Getter
	@Setter
	public int z;
	
	public ChunkVector(@NonNull int x, @NonNull int z) {
		this.x = x;
		this.z = z;
	}
	
	public ChunkVector(@NonNull Vector3i vector) {
		this.x = vector.getX() >> 4;
		this.z = vector.getZ() >> 4;
	}
	
	public ChunkVector(@NonNull Vector3d vector) {
		this.x = vector.asInt().getX() >> 4;
		this.z = vector.asInt().getZ() >> 4;
	}
	
}