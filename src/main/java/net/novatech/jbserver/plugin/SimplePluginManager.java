package net.novatech.jbserver.plugin;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import net.novatech.jbserver.event.*;
import net.novatech.jbserver.event.EventListener;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.utils.Logger;
import net.novatech.jbserver.utils.Utils;

public class SimplePluginManager implements PluginManager{

    public static boolean useTimings = false;
    protected Map<String, Plugin> plugins = new LinkedHashMap<>();
    protected Map<String, PluginLoader> fileAssociations = new HashMap<>();
    private Server server;
	
    public SimplePluginManager(Server server) {this.server = server;}
    
	@Override
	public Plugin getPlugin(String name) {
        if (this.plugins.containsKey(name)) {
            return this.plugins.get(name);
        }
        return null;
	}

	@Override
	public boolean registerInterface(Class<? extends PluginLoader> clazz) {
        if (clazz != null) {
            try {
                Constructor constructor = clazz.getDeclaredConstructor(Server.class);
                constructor.setAccessible(true);
                this.fileAssociations.put(clazz.getName(), (PluginLoader) constructor.newInstance(this.server));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
	}

	@Override
	public Map<String, Plugin> getPlugins() {
		return plugins;
	}

	@Override
	public Plugin loadPlugin(String path) {
		return this.loadPlugin(path, null);
	}

	@Override
	public Plugin loadPlugin(File file) {
		return this.loadPlugin(file, null);
	}

	@Override
	public Plugin loadPlugin(String path, Map<String, PluginLoader> loaders) {
		return this.loadPlugin(new File(path), loaders);
	}

	@Override
	public Plugin loadPlugin(File file, Map<String, PluginLoader> loaders) {
		for (PluginLoader loader : (loaders == null ? this.fileAssociations : loaders).values()) {
            for (Pattern pattern : loader.getPluginFilters()) {
                if (pattern.matcher(file.getName()).matches()) {
                    PluginDescription description = loader.getPluginDescription(file);
                    if (description != null) {
                        try {
                            Plugin plugin = loader.loadPlugin(file);
                            if (plugin != null) {
                                this.plugins.put(plugin.getDescription().getName(), plugin);
                                return plugin;
                            }
                        } catch (Exception e) {
                            return null;
                        }
                    }
                }
            }
        }
        return null;
	}

	@Override
	public Map<String, Plugin> loadPlugins(String path) {
		return this.loadPlugins(new File(path));
	}

	@Override
	public Map<String, Plugin> loadPlugins(File file) {
		return this.loadPlugins(file,null);
	}

	@Override
	public Map<String, Plugin> loadPlugins(String path, List<String> newLoaders) {
		return this.loadPlugins(new File(path),newLoaders);
	}

	@Override
	public Map<String, Plugin> loadPlugins(File file, List<String> newLoaders) {
		return this.loadPlugins(file, newLoaders, false);
	}

	@Override
	public Map<String, Plugin> loadPlugins(File dictionary, List<String> newLoaders, boolean include) {
		if (dictionary.isDirectory()) {
            Map<String, File> plugins = new LinkedHashMap<>();
            Map<String, Plugin> loadedPlugins = new LinkedHashMap<>();
            Map<String, List<String>> dependencies = new LinkedHashMap<>();
            Map<String, List<String>> softDependencies = new LinkedHashMap<>();
            Map<String, PluginLoader> loaders = new LinkedHashMap<>();
            if (newLoaders != null) {
                for (String key : newLoaders) {
                    if (this.fileAssociations.containsKey(key)) {
                        loaders.put(key, this.fileAssociations.get(key));
                    }
                }
            } else {
                loaders = this.fileAssociations;
            }

            for (final PluginLoader loader : loaders.values()) {
                for (File file : dictionary.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        for (Pattern pattern : loader.getPluginFilters()) {
                            if (pattern.matcher(name).matches()) {
                                return true;
                            }
                        }
                        return false;
                    }
                })) {
                    if (file.isDirectory() && !include) {
                        continue;
                    }
                    try {
                        PluginDescription description = loader.getPluginDescription(file);
                        if (description != null) {
                            String name = description.getName();
                            if (name.toLowerCase().contains("jbserver") || name.toLowerCase().contains("minecraft") || name.toLowerCase().contains("mojang") || name.toLowerCase().contains("spigot") || name.toLowerCase().contains("bungeecord")) {
                                this.server.getLogger().error("Couldn't load plugin " + name + ": Restricted name");
                                continue;
                            } else if (name.contains(" ")) {
                                this.server.getLogger().warning("Plugin " + name + " uses some spaces, that isn't allowed");
                            }

                            if (plugins.containsKey(name) || this.getPlugin(name) != null) {
                                this.server.getLogger().error("Tried to load plugin " + name + ", but couldn't: same plugin already exists");
                                continue;
                            }

                            boolean compatible = false;

                            for (String version : description.getCompatibleAPIs()) {

                                if (!Pattern.matches("[0-9]\\.[0-9]\\.[0-9]", version)) {
                                    this.server.getLogger().error("Could't load plugin " + name + ": Wrong API format");
                                    continue;
                                }

                                String[] versionArray = version.split("\\.");
                                String[] apiVersion = this.server.getApiVersion().split("\\.");
                                if (!Objects.equals(Integer.valueOf(versionArray[0]), Integer.valueOf(apiVersion[0]))) {
                                    continue;
                                }
                                if (Integer.valueOf(versionArray[1]) > Integer.valueOf(apiVersion[1])) {
                                    continue;
                                }

                                compatible = true;
                                break;
                            }

                            if (!compatible) {
                                this.server.getLogger().error("Couldn't load plugin " + name +": Incompatible API version");
                            }

                            plugins.put(name, file);

                            softDependencies.put(name, description.getSoftDepend());

                            dependencies.put(name, description.getDepend());

                            for (String before : description.getLoadBefore()) {
                                if (softDependencies.containsKey(before)) {
                                    softDependencies.get(before).add(name);
                                } else {
                                    List<String> list = new ArrayList<>();
                                    list.add(name);
                                    softDependencies.put(before, list);
                                }
                            }
                        }
                    } catch (Exception e) {
                        this.server.getLogger().error("Couldn't load " +file.getName()+ " in folder " + dictionary.toString() + ": " + Utils.getExceptionMessage(e));
                        Logger logger = this.server.getLogger();
                        if (logger != null) {
                            logger.logException(e);
                        }
                    }
                }
            }

            while (!plugins.isEmpty()) {
                boolean missingDependency = true;
                for (String name : new ArrayList<>(plugins.keySet())) {
                    File file = plugins.get(name);
                    if (dependencies.containsKey(name)) {
                        for (String dependency : new ArrayList<>(dependencies.get(name))) {
                            if (loadedPlugins.containsKey(dependency) || this.getPlugin(dependency) != null) {
                                dependencies.get(name).remove(dependency);
                            } else if (!plugins.containsKey(dependency)) {
                                this.server.getLogger().error("Couldn't load plugin " + name + ": Unknown dependency");
                                break;
                            }
                        }

                        if (dependencies.get(name).isEmpty()) {
                            dependencies.remove(name);
                        }
                    }

                    if (softDependencies.containsKey(name)) {
                        for (String dependency : new ArrayList<>(softDependencies.get(name))) {
                            if (loadedPlugins.containsKey(dependency) || this.getPlugin(dependency) != null) {
                                softDependencies.get(name).remove(dependency);
                            }
                        }

                        if (softDependencies.get(name).isEmpty()) {
                            softDependencies.remove(name);
                        }
                    }

                    if (!dependencies.containsKey(name) && !softDependencies.containsKey(name)) {
                        plugins.remove(name);
                        missingDependency = false;
                        Plugin plugin = this.loadPlugin(file, loaders);
                        if (plugin != null) {
                            loadedPlugins.put(name, plugin);
                        } else {
                            this.server.getLogger().error("Couldn't load plugin " + name);
                        }
                    }
                }

                if (missingDependency) {
                    for (String name : new ArrayList<>(plugins.keySet())) {
                        File file = plugins.get(name);
                        if (!dependencies.containsKey(name)) {
                            softDependencies.remove(name);
                            plugins.remove(name);
                            missingDependency = false;
                            Plugin plugin = this.loadPlugin(file, loaders);
                            if (plugin != null) {
                                loadedPlugins.put(name, plugin);
                            } else {
                                this.server.getLogger().error("Couldn't load plugin " + name);
                            }
                        }
                    }

                    if (missingDependency) {
                        for (String name : plugins.keySet()) {
                            this.server.getLogger().error("Couldn't load plugin " + name + ": Circular dependency detected");
                        }
                        plugins.clear();
                    }
                }
            }

            return loadedPlugins;
        } else {

            return new HashMap<>();
        }
	}

	@Override
	public boolean isPluginLoaded(Plugin plugin) {
        return (plugin != null && this.plugins.containsKey(plugin.getDescription().getName())) ? plugin.isEnabled() : false;
	}

	@Override
	public void enablePlugin(Plugin plugin) {
		if (!plugin.isEnabled()) {
            try {
                plugin.getPluginLoader().enablePlugin(plugin);
            } catch (Exception e) {
                Logger logger = this.server.getLogger();
                if (logger != null) {
                    logger.logException(e);
                }
                this.disablePlugin(plugin);
            }
        }
	}

	@Override
	public void disablePlugin(Plugin plugin) {
        if (plugin.isEnabled()) {
            try {
                plugin.getPluginLoader().disablePlugin(plugin);
            } catch (Exception e) {
                Logger logger = this.server.getLogger();
                if (logger != null) {
                    logger.logException(e);
                }
            }

            this.server.getScheduler().cancelTask(plugin);
            this.server.getEventManager().unregisterListeners(plugin);
        }
	}

	@Override
	public void disablePlugins() {
		for (Plugin plugin : this.getPlugins().values()) {
            this.disablePlugin(plugin);
        }
	}

	@Override
	public void clearPlugins() {
        this.disablePlugins();
        this.plugins.clear();
        this.fileAssociations.clear();
	}

	@Override
	public boolean useTimings() {
		return useTimings;
	}

	@Override
	public void setUseTimings(boolean value) {
		useTimings = value;
	}

}
