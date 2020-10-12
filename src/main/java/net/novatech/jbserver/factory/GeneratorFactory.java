package net.novatech.jbserver.factory;

import java.util.*;

import net.novatech.jbserver.world.WorldType;
import net.novatech.jbserver.world.generator.*;

public class GeneratorFactory implements Factory {

	public Map<String, Generator> generators = new HashMap<String, Generator>();
	
	@Override
	public void init(FactoryManager manager) {
		registerGenerator(WorldType.VOID.getWorldType(), new VoidGenerator());
	}
	
	public void registerGenerator(String id, Generator generator) {
		if(!generators.containsKey(id)) {
			generators.put(id, generator);
		}
	}
	
	public Generator getGenerator(String id) {
		if(generators.containsKey(id)) {
			return generators.get(id);
		}
		return null;
	}
	

}
