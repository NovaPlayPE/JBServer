package net.novatech.jbserver.factory;

import java.util.*;

import net.novatech.jbserver.world.WorldType;
import net.novatech.jbserver.world.generator.*;
import net.novatech.library.reflection.Reflect;

public class GeneratorFactory implements Factory {

	public Map<String, Class<? extends Generator>> generators = new HashMap<String, Class<? extends Generator>>();
	
	@Override
	public void init(FactoryManager manager) {
		registerGenerator(WorldType.VOID.getWorldType(), VoidGenerator.class);
		registerGenerator(WorldType.NORMAL.getWorldType(), NormalGenerator.class);
	}
	
	public void registerGenerator(String id, Class<? extends Generator> generator) {
		if(!generators.containsKey(id)) {
			generators.put(id, generator);
		}
	}
	
	public Generator getGenerator(String id) {
		if(generators.containsKey(id)) {
			Generator generator = Reflect.on(generators.get(id)).get();
			return generator;
		}
		return null;
	}
	

}
