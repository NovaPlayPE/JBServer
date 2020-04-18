package net.novaplay.jbserver.plugin.java;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import net.novaplay.jbserver.config.Config;
import net.novaplay.jbserver.plugin.Plugin;
import net.novaplay.jbserver.plugin.PluginDescription;
import net.novaplay.jbserver.plugin.PluginLoader;
import net.novaplay.jbserver.server.Server;
import net.novaplay.jbserver.utils.Color;
import net.novaplay.jbserver.utils.Logger;
import net.novaplay.jbserver.utils.Utils;

public class JavaPlugin implements Plugin {

	
	private Logger logger = null;
	private boolean initialized = false;
	private boolean enabled = false;
	private Config config = null;
	private File file = null;
	private File configurationFile = null;
	private File dataFolder = null;
	private Server server = null;
	private PluginDescription description = null;
	private PluginLoader loader = null;
	
	@Override
	public void onLoad() {
	}

	@Override
	public void onEnable() {
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean value) {
		if(this.enabled != value) {
			this.enabled = value;
			if(this.enabled) {
				onEnable();
			} else {
				onDisable();
			}
		}
	}

	@Override
	public void onDisable() {
	}

	@Override
	public boolean isDisabled() {
		return !this.enabled;
	}

	@Override
	public File getDataFolder() {
		return this.dataFolder;
	}

	@Override
	public PluginDescription getDescription() {
		return this.description;
	}

	public void init(PluginLoader loader, Server server,PluginDescription desc, File data, File file) {
		if(!initialized) {
			initialized = true;
			this.loader = loader;
			this.server = server;
			this.description = desc;
			this.dataFolder = data;
			this.file = file;
			this.configurationFile = new File(this.dataFolder,"config.yml");
			this.logger = this.server.getLogger();
		}
	}
	
	@Override
	public InputStream getResource(String filename) {
		return this.getClass().getClassLoader().getResourceAsStream(filename);
	}

	@Override
	public boolean saveResource(String filename) {
		return saveResource(filename,false);
	}

	@Override
	public boolean saveResource(String filename, boolean replace) {
		return saveResource(filename, filename, replace);
	}

	@Override
	public boolean saveResource(String filename, String outputName, boolean replace) {
		File out = new File(dataFolder, outputName);
		if (!out.exists() || replace) {
			try (InputStream resource = getResource(filename)) {
				if (resource != null) {
					File outFolder = out.getParentFile();
					if (!outFolder.exists()) {
						outFolder.mkdirs();
						}
					Utils.writeFile(out, resource);
					return true;
					}
				} catch (IOException e) {
					Server.getInstance().getLogger().logException(e);
				}
			}
        return false;
	}

	@Override
	public Config getConfig() {
		if(this.config == null) {
			reloadConfig();
		}
		return this.config;
	}

	@Override
	public void saveConfig() {
		boolean save = getConfig().save();
		if(!save) {
			this.getServer().getLogger().error(Color.RED + "Couldn't save configuration file to " + this.configurationFile.toString());
		}
	}

	@Override
	public void saveDefaultConfig() {
		if(!this.configurationFile.exists()) {
			this.saveResource("config.yml", false);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reloadConfig() {
		this.config = new Config(this.configurationFile);
		InputStream configStream = this.getResource("config.yml");
		if (configStream != null) {
			DumperOptions dumperOptions = new DumperOptions();
			dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
			Yaml yaml = new Yaml(dumperOptions);
			try {
				this.config.setDefault(yaml.loadAs(Utils.readFile(this.configurationFile), LinkedHashMap.class));
			} catch (IOException e) {
				Server.getInstance().getLogger().logException(e);
			}
		}
	}

	@Override
	public Server getServer() {
		return this.server;
	}

	@Override
	public String getName() {
		return this.getDescription().getName();
	}

	@Override
	public Logger getLogger() {
		return this.logger;
	}

	@Override
	public PluginLoader getPluginLoader() {
		return this.loader;
	}

}
