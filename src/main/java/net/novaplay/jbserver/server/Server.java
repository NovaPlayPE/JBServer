package net.novaplay.jbserver.server;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.Getter;
import net.novaplay.jbserver.config.Config;
import net.novaplay.jbserver.config.ConfigSection;
import net.novaplay.jbserver.network.Network;
import net.novaplay.jbserver.event.HandlerList;
import net.novaplay.jbserver.factory.FactoryManager;
import net.novaplay.jbserver.manager.PathManager;
import net.novaplay.jbserver.plugin.PluginManager;
import net.novaplay.jbserver.plugin.SimplePluginManager;
import net.novaplay.jbserver.plugin.java.JavaPluginLoader;
import net.novaplay.jbserver.utils.ConsoleColor;
import net.novaplay.jbserver.plugin.Plugin;
import net.novaplay.jbserver.command.CommandMap;
import net.novaplay.jbserver.command.ConsoleCommandSender;
import net.novaplay.jbserver.JBMain;
import net.novaplay.jbserver.player.*;
import net.novaplay.jbserver.scheduler.ServerScheduler;
import net.novaplay.jbserver.server.settings.ServerSettings;
import net.novaplay.jbserver.utils.Logger;

public class Server {
	
	private static Server instance = null;

	
	private ServerSettings settings = null;
	private ServerScheduler scheduler = null;
	private Network network = null;
	private Logger logger = null;
	
	@Getter
	private FactoryManager factoryManager = null;

	private PluginManager pluginManager = null;
	
	private ConsoleCommandSender commandSender = new ConsoleCommandSender();
	private CommandMap commandMap = null;
	
	private boolean isRunning = true;
	private boolean isStopped = false;
	private Config properties;
	private int tickCounter = 0;
	private long nextTick = 0;
	
	private ExecutorService pool = Executors.newCachedThreadPool();
	private ExecutorService synchronizedPool = Executors.newSingleThreadExecutor();
	
	public Server(String filePath, String dataPath, String pluginPath, String worldPath) {
		instance = this;
		PathManager.setFilePath(filePath);
		if (!new File(pluginPath).exists()) new File(pluginPath).mkdirs();
		if(!new File(worldPath).exists()) new File(worldPath).mkdirs();
		PathManager.dataPath = new File(dataPath).getAbsolutePath() + "/";
		PathManager.pluginPath = new File(pluginPath).getAbsolutePath() + "/";
		PathManager.worldPath = new File(worldPath).getAbsolutePath() + "/";
		this.logger = new Logger(dataPath + "server.log");
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {}));
		Thread.currentThread().setName("JBServer");
		
		this.properties = new Config(PathManager.dataPath + "server.properties", Config.PROPERTIES, new ConfigSection() {
			{
				//adresses
				put("server-ip", "0.0.0.0");
				put("java-port",25565);
				put("bedrock-port", 19132);
				//motd
				put("motd", "Cool JBServer");
				put("motd-underline", "Powered by JBServer");
				put("motd-repeat",1);
				put("max-players", 40);
				put("max-players-plus-1",false);
				put("view-distance",10);
				put("default-gamemode",0);
				//world
				put("spawn-protection",true);
				put("spawn-protection-radius",20);
				put("world-name", "world");
				put("world-type", "normal");
				put("world-seed", 0);
				put("allow-nether",false);
				put("allow-end",false);
				//query
				put("enable-query",true);
				put("enable-rcon",false);
				put("online-mode",true);
			}
		});
		this.settings = new ServerSettings(this.properties);
		
		enableFactory();
		enableNetwork();
		
		this.commandSender = new ConsoleCommandSender();
		this.commandMap = new CommandMap(this);
		this.pool.execute(() -> {
			Thread.currentThread().setName("JBServer-Commands");
			this.getCommandMap().dispatch(this.commandSender,
					command -> getLogger().error("Command " + command + " not found"));
		});
		
		scheduler = new ServerScheduler();
		enablePlugins();

		this.properties.save(true);

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			this.isStopped = true;
			
			getLogger().info("Shutting down network");
			getNetwork().endup();
			
			getLogger().info("Disabling all plugins");
			getPluginManager().disablePlugins();

			getLogger().info("Disabling event handlers");
			HandlerList.unregisterAll();

			getLogger().info("Stopping all tasks");
			this.scheduler.cancelAllTasks();
			this.scheduler.mainThreadHeartbeat(Integer.MAX_VALUE);
		}));
		start();
	}
	
	public void enableFactory() {
		this.factoryManager = new FactoryManager();
		this.factoryManager.init(this);
	}
	
	public void enableNetwork() {
		int port1 = this.settings.getJavaPort();
		int port2 = this.settings.getBedrockPort();
		if(port1 == port2) {
			this.network = new Network(this,port1);
		} else {
			this.network = new Network(this,port1,port2);
		}
	}
	
	public void enablePlugins() {
		this.logger.info(ConsoleColor.GREEN + "Loading all plugins");
		this.pluginManager = new SimplePluginManager(this);
		this.pluginManager.registerInterface(JavaPluginLoader.class);
		this.pluginManager.loadPlugins(PathManager.pluginPath);
		
		for (Plugin plugin : getPluginManager().getPlugins().values()) {
			if (!plugin.isEnabled()) {
				getPluginManager().enablePlugin(plugin);
			}
		}
	}
	
	public CommandMap getCommandMap() {
		return this.commandMap;
	}
	
	public void start() {
		getLogger().info(ConsoleColor.GREEN + "Server has been started!, Type help for help (?)");
		this.nextTick = System.currentTimeMillis();
		while (this.isRunning) {
			try {
				long tick = System.currentTimeMillis();
				if((tick - this.nextTick) < -5) {
					return;
				}
				++this.tickCounter;
				this.scheduler.mainThreadHeartbeat(this.tickCounter);
			} catch (RuntimeException e) {
				getLogger().logException(e);
			}
			
			try {
				Thread.sleep(1);
			} catch(InterruptedException ex) {
				getLogger().logException(ex);
			}
		}
	}

	public void shutdown() {
		this.synchronizedPool.shutdown();
		this.pool.shutdown();
		this.isRunning = false;
		System.exit(0);
	}
	
	public Logger getLogger() { return this.logger; }
	public static Server getInstance() { return instance; }
	
	/**
	 * 
	 * @Managers
	 */
	
	public ServerScheduler getScheduler() { return this.scheduler; }
	public PluginManager getPluginManager() { return this.pluginManager; }
	
	public String getVersion() { return JBMain.VERSION;}
	public String getApiVersion() { return JBMain.API_VERSION;}
	
	public ServerSettings getServerSettings() { return this.settings; }
	
	public Network getNetwork() {return this.network;}
	
	
}
