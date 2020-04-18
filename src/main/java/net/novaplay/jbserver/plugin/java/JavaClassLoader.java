package net.novaplay.jbserver.plugin.java;

import java.net.URLClassLoader;
import java.net.*;
import java.util.*;
import java.io.File;

public class JavaClassLoader extends URLClassLoader {
	
	private JavaPluginLoader loader;
	private Map<String, Class> classes = new HashMap<>();
	
	public JavaClassLoader(JavaPluginLoader loader, ClassLoader parent, File file) throws MalformedURLException {
		super(new URL[]{file.toURI().toURL()}, parent);
		this.loader = loader;	
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		return this.findClass(name, true);	
	}
	
	protected Class<?> findClass(String name, boolean checkGlobal) throws ClassNotFoundException {
		if (name.startsWith("net.novaplay.jbproxy.") || name.startsWith("net.minecraft.")) {
			throw new ClassNotFoundException(name);
		}
		Class<?> result = classes.get(name);
		if(result == null) {
			if(checkGlobal) {
				result = loader.getClassByName(name);	
			}
			if(result == null) {
				result = super.findClass(name);
				if (result != null) {
					loader.setClass(name, result);
				}
			}
			classes.put(name, result);
		}
		return result;
	}
	
	Set<String> getClasses() {
		return classes.keySet();
	}

}