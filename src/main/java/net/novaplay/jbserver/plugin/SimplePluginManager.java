package net.novaplay.jbserver.plugin;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

import net.novaplay.jbserver.event.*;
import net.novaplay.jbserver.event.EventListener;
import net.novaplay.jbserver.server.Server;
import net.novaplay.jbserver.utils.Logger;
import net.novaplay.jbserver.utils.Utils;

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
            HandlerList.unregisterAll(plugin);
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
	public void callEvent(Event event) {
        try {
            for (PluginListener listener : getEventListeners(event.getClass()).getPluginListeners()) {
                if (!listener.getPlugin().isEnabled()) {
                    continue;
                }

                try {
                    listener.callEvent(event);
                } catch (Exception e) {
                	this.server.getLogger().error("Couldn't pass event" + event.getEventName() + " to " + listener.getPlugin().getDescription().getFullName() + ": " + e.getMessage() + " on " + listener.getListener().getClass().getName());
                    Logger logger = this.server.getLogger();
                    if (logger != null) {
                        logger.logException(e);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            Server.getInstance().getLogger().logException(e);
        }
	}

	@Override
	public void registerEvents(EventListener listener, Plugin plugin) {
        if (!plugin.isEnabled()) {
            throw new PluginException("Plugin attempted to register " + listener.getClass().getName() + " while not enabled");
        }
        Map<Class<? extends Event>, Set<PluginListener>> ret = new HashMap<>();
		Set<Method> methods;
        try {
            Method[] publicMethods = listener.getClass().getMethods();
            Method[] privateMethods = listener.getClass().getDeclaredMethods();
            methods = new HashSet<>(publicMethods.length + privateMethods.length, 1.0f);
            Collections.addAll(methods, publicMethods);
            Collections.addAll(methods, privateMethods);
        } catch (NoClassDefFoundError e) {
            plugin.getLogger().error("Plugin " + plugin.getDescription().getFullName() + " has failed to register events for " + listener.getClass() + " because " + e.getMessage() + " does not exist.");
            return;
        }

        for (final Method method : methods) {
            final EventHandler eh = method.getAnnotation(EventHandler.class);
            if (eh == null) continue;
            if (method.isBridge() || method.isSynthetic()) {
                continue;
            }
            final Class<?> checkClass;

            if (method.getParameterTypes().length != 1 || !Event.class.isAssignableFrom(checkClass = method.getParameterTypes()[0])) {
                plugin.getLogger().error(plugin.getDescription().getFullName() + " attempted to register an invalid EventHandler method signature \"" + method.toGenericString() + "\" in " + listener.getClass());
                continue;
            }

            final Class<? extends Event> eventClass = checkClass.asSubclass(Event.class);
            method.setAccessible(true);
            Set<PluginListener> eventSet = ret.get(eventClass);
            if (eventSet == null) {
                eventSet = new HashSet<>();
                ret.put(eventClass, eventSet);
            }

            for (Class<?> clazz = eventClass; Event.class.isAssignableFrom(clazz); clazz = clazz.getSuperclass()) {
                // This loop checks for extending deprecated events
                if (clazz.getAnnotation(Deprecated.class) != null) {
                    //if (Boolean.valueOf(String.valueOf(this.server.getConfig("settings.deprecated-verbpse", true)))) {
                    //    this.server.getLogger().warning("Plugin " + plugin.getName()+ "has registered a listener for "+ clazz.getName() + "on method " + listener.getClass().getName() + "." + method.getName() + "(), but event is deprecated");
                    //}
                    break;
                }
            }
            this.registerEvent(eventClass, listener, eh.priority(), new EventMethodExecutor(method), plugin, eh.ignoreCancelled());
        }
	}

	@Override
	public void registerEvent(Class<? extends Event> event, EventListener listener, EventPriority priority, EventExecutor executor, Plugin plugin) throws PluginException {
		 this.registerEvent(event, listener, priority, executor, plugin, false);
		
	}

	@Override
	public void registerEvent(Class<? extends Event> event, EventListener listener, EventPriority priority, EventExecutor executor, Plugin plugin, boolean ignoreCancelled) throws PluginException {
        if (!plugin.isEnabled()) {
            throw new PluginException("Plugin attempted to register " + event + " while not enabled");
        }
        try {
            this.getEventListeners(event).register(new PluginListener(listener, executor, priority, plugin, ignoreCancelled));
        } catch (IllegalAccessException e) {
            Server.getInstance().getLogger().logException(e);
        }
	}

	@Override
	public HandlerList getEventListeners(Class<? extends Event> type) throws IllegalAccessException {
		 try {
	            Method method = getRegistrationClass(type).getDeclaredMethod("getHandlers");
	            method.setAccessible(true);
	            return (HandlerList) method.invoke(null);
	        } catch (Exception e) {
	            throw new IllegalAccessException(Utils.getExceptionMessage(e));
	        }
	}

	@Override
	public Class<? extends Event> getRegistrationClass(Class<? extends Event> clazz) throws IllegalAccessException {
	       try {
	            clazz.getDeclaredMethod("getHandlers");
	            return clazz;
	        } catch (NoSuchMethodException e) {
	            if (clazz.getSuperclass() != null
	                    && !clazz.getSuperclass().equals(Event.class)
	                    && Event.class.isAssignableFrom(clazz.getSuperclass())) {
	                return getRegistrationClass(clazz.getSuperclass().asSubclass(Event.class));
	            } else {
	                throw new IllegalAccessException("Unable to find handler list for event " + clazz.getName() + ". Static getHandlers method required!");
	            }
	        }
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
