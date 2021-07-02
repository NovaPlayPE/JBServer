package net.novatech.jbserver.world.provider;

import java.lang.reflect.Constructor;
import java.util.*;

import net.novatech.library.reflection.Reflect;

public class WorldProviderManager {
	
	public static String ANVIL = "anvil";
	public static String LEVELDB = "leveldb";
	public static String NPWORLD = "npworld";
	
	public static Map<String, Class<? extends BaseWorldProvider>> providers = new HashMap<String, Class<? extends BaseWorldProvider>>();
	
	
	public static void registerWorldProvider(String id, Class<? extends BaseWorldProvider> provider) {
		providers.put(id, provider);
	}
	
	public static BaseWorldProvider testProvider(String worldName) {
		BaseWorldProvider provajd =  Reflect.on(providers.get(worldName)).get();
		if(provajd.isValid()) {
			return provajd;
		}
		return null;
	}
	
	public static BaseWorldProvider tryGetProvider(String worldName) {
		BaseWorldProvider provider = null;
		for(Class<? extends BaseWorldProvider> provid : providers.values()) {
			try {
				Constructor<? extends BaseWorldProvider> construct = provid.getConstructor();
				construct.setAccessible(true);
				BaseWorldProvider provajd = construct.newInstance(worldName);
				if(provajd.isValid()) {
					provider = provajd;
				}
			} catch(Exception e) {
				
			}
		}
		return provider;
	}
	
	public static BaseWorldProvider getDefaultProvider(String string, String path) {
		BaseWorldProvider provider = null;
		Class<? extends BaseWorldProvider> provid = providers.get(string);
		try {
				Constructor<? extends BaseWorldProvider> construct = provid.getConstructor();
				construct.setAccessible(true);
				BaseWorldProvider provajd = construct.newInstance(path);
				if(provajd.isValid()) {
					provider = provajd;
				}
		}catch(Exception e) {}
		return provider;
	}
	
}