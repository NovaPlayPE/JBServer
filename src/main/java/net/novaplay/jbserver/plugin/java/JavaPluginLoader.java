package net.novaplay.jbserver.plugin.java;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

import net.novaplay.jbserver.plugin.Plugin;
import net.novaplay.jbserver.plugin.PluginDescription;
import net.novaplay.jbserver.plugin.PluginException;
import net.novaplay.jbserver.plugin.PluginLoader;
import net.novaplay.jbserver.server.Server;
import net.novaplay.jbserver.utils.Utils;

public class JavaPluginLoader implements PluginLoader {

	private Server server = null;
    private Map<String, Class> classes = new HashMap<String, Class>();
    private Map<String, JavaClassLoader> classLoaders = new HashMap<String, JavaClassLoader>();
	
    public JavaPluginLoader(Server server) { this.server = server; }
    
	@Override
	public Plugin loadPlugin(String filename) throws Exception {
		return loadPlugin(new File(filename));
	}

	@Override
	public Plugin loadPlugin(File file) throws Exception {
		PluginDescription dscrpt = getPluginDescription(file);
		if(dscrpt != null) {
			server.getLogger().info("Loading plugin " + dscrpt.getFullName());
			File folder = new File(file.getParentFile(),dscrpt.getName());
			String className = dscrpt.getMain();
			JavaClassLoader loader = new JavaClassLoader(this, this.getClass().getClassLoader(),file);
			classLoaders.put(dscrpt.getName(),loader);
			JavaPlugin plugin = null;
			try {
				Class clazz = loader.loadClass(className);
				try {
					Class<? extends JavaPlugin> pluginClass = clazz.asSubclass(JavaPlugin.class);
					plugin = pluginClass.newInstance();
					setup(plugin,dscrpt, folder,file);
					return plugin;
				} catch(ClassCastException e) {
					throw new PluginException("Main class "+ dscrpt.getMain() +" should extends JavaPlugin, but extends " + clazz.getSuperclass().getSimpleName());
				} catch(InstantiationException | IllegalAccessException e) {
					Server.getInstance().getLogger().logException(e);
				}
			} catch(ClassNotFoundException e) {
				throw new PluginException("Class " + dscrpt.getMain() + " not found, plugin cannot be loaded");
			}
		}
		return null;
	}
	
	public void setup(JavaPlugin plugin, PluginDescription desc, File data, File file) {
		plugin.init(this, this.server, desc,data,file);
		plugin.onLoad();
	}

	@Override
	public PluginDescription getPluginDescription(String filename) {
		return getPluginDescription(new File(filename));
	}

	@Override
	public PluginDescription getPluginDescription(File file) {
		try {
			JarFile jar = new JarFile(file);
			JarEntry entry = jar.getJarEntry("jbproxy.yml");
			if (entry == null) {
				entry = jar.getJarEntry("plugin.yml");
				if (entry == null) {
					return null;
				}
			}
			InputStream stream = jar.getInputStream(entry);
			return new PluginDescription(Utils.readFile(stream));
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public Pattern[] getPluginFilters() {
		return new Pattern[]{Pattern.compile("^.+\\.jar$")};
	}

	@Override
	public void enablePlugin(Plugin plugin) {
		if(plugin instanceof JavaPlugin) {
			if(!plugin.isEnabled()) {
				server.getLogger().info("Enabled plugin " + plugin.getDescription().getFullName());
				((JavaPlugin)plugin).setEnabled(true);
			}
		}
	}

	@Override
	public void disablePlugin(Plugin plugin) {
		if(plugin instanceof JavaPlugin) {
			if(plugin.isEnabled()) {
				server.getLogger().info("Disabled plugin " + plugin.getDescription().getFullName());
				((JavaPlugin)plugin).setEnabled(false);
			}
		}
	}
	
	public Class<?> getClassByName(final String name) throws ClassNotFoundException{
		Class<?> clazz = classes.get(name);
		try {
			for(JavaClassLoader loader : classLoaders.values()) {
				try {
					clazz = loader.findClass(name,false);
				} catch(NullPointerException e) {
				}
			}
			return clazz;
		} catch(NullPointerException s) {
			return null;
		}
	}
	
	public void setClass(String name, final Class<?> clazz) {
		if(!classes.containsKey(name)) {
			classes.put(name,clazz);
		}
	}
	
	protected void removeClass(String name) {
		Class<?> clazz = classes.remove(name);
	}

}
