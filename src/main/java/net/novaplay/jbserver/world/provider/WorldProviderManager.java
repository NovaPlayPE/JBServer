package net.novaplay.jbserver.world.provider;

import net.novaplay.jbserver.world.provider.impl.BaseWorldProvider;

import java.lang.reflect.Constructor;
import java.util.*;

public class WorldProviderManager {
	
	public static Map<String, Class<? extends BaseWorldProvider>> providers = new HashMap<String, Class<? extends BaseWorldProvider>>();
	
	
	public static void registerWorldProvider(String id, Class<? extends BaseWorldProvider> provider) {
		providers.put(id, provider);
	}
	
	public BaseWorldProvider tryGetProvider(String path) {
		BaseWorldProvider provider = null;
		for(Class<? extends BaseWorldProvider> provid : providers.values()) {
			try {
				Constructor<? extends BaseWorldProvider> construct = provid.getConstructor();
				construct.setAccessible(true);
				BaseWorldProvider provajd = construct.newInstance(path);
				if(provajd.isValid()) {
					provider = provajd;
				}
			} catch(Exception e) {
				
			}
		}
		return provider;
	}
	
}