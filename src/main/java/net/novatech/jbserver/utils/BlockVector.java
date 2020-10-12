package net.novatech.jbserver.utils;

import lombok.NonNull;
import net.novatech.library.math.Vector3i;

public class BlockVector extends Vector3i{

	public BlockVector(@NonNull int x, @NonNull int y, @NonNull int z) {
		super(x, y, z);
	}

}