package net.novatech.jbserver.factory;

import net.novatech.jbserver.entity.Entity;
import net.novatech.jbserver.entity.EntityBuilder;
import net.novatech.jbserver.entity.EntityType;
import net.novatech.jbserver.entity.passive.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class EntityFactory implements Factory {
	
	public static String COW_ID = "Cow";
	public static String CHICKEN_ID = "Chicken";
	public static String SHEEP_ID = "Sheep";
	public static String PIG_ID = "Pig";
	public static String SLIME_ID = "Slime";

	public static HashMap<String, Class<? extends Entity>> entityClasses = new HashMap<String, Class<? extends Entity>>();
	
	private FactoryManager manager;
	
	@Override
	public void init(FactoryManager manager) {
		this.manager = manager;
		registerEntities(COW_ID, EntityCow.class);
	}
	
	/**
	 * This method allows to register new entity classes, even custom ones.
	 * Example: registerEntities("CustomEntity", CustomEntity.class);
	 * Note: It would be super to have EntityType writen in anonymous constructor function, not externally
	 * 
	 * @param entityClassName
	 * @param slazz
	 */
	public static void registerEntities(String entityClassName, Class<? extends Entity> slazz) {
		if(!entityClasses.containsKey(entityClassName)) {
			entityClasses.put(entityClassName, slazz);
			return;
		}
	}
	
	/**
	 * Returns your Entity class, constructed by entity class id and defined by builder class
	 * 
	 * @param id
	 * @param builder
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Entity newEntity(String id, EntityBuilder builder) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Entity e;
		Class<? extends Entity> clazz = entityClasses.get(id);
		Constructor<? extends Entity> constructor = clazz.getConstructor();
		e = constructor.newInstance();
		e.init(builder);
		return e;
	}

}
