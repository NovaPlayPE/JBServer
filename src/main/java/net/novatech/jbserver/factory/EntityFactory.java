package net.novatech.jbserver.factory;

import net.novatech.jbserver.entity.Entity;
import net.novatech.jbserver.entity.EntityBuilder;
import net.novatech.jbserver.entity.EntityType;
import net.novatech.jbserver.entity.passive.*;

import java.util.*;

public class EntityFactory implements Factory {

	public static HashMap<String, Class<? extends Entity>> entityClasses = new HashMap<String, Class<? extends Entity>>();
	
	private FactoryManager manager;
	
	@Override
	public void init(FactoryManager manager) {
		this.manager = manager;
		registerEntities("Cow", EntityCow.class);
	}
	
	public static void registerEntities(String entityClassName, Class<? extends Entity> slazz) {
		if(!entityClasses.containsKey(entityClassName)) {
			entityClasses.put(entityClassName, slazz);
			return;
		}
	}

}
