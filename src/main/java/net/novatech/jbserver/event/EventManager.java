package net.novatech.jbserver.event;

import net.novatech.jbserver.plugin.Plugin;
import net.novatech.jbserver.plugin.java.JavaPlugin;
import net.novatech.jbserver.server.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventManager {
	
	private Server server;
	private HashMap<Plugin,ArrayList<EventListener>> eventListeners = new HashMap<Plugin,ArrayList<EventListener>>();
	
	public EventManager(Server server) {
		this.server = server;
	}
	
	public void registerEventListener(Plugin plugin, EventListener listener) {
		if(!plugin.isEnabled()) {
			throw new EventException("Tried to register listener, but plugin is disabled");
		}
		if(eventListeners.containsKey(plugin)) {
			ArrayList<EventListener> lstnrs = new ArrayList<EventListener>(eventListeners.get(plugin));
			for(EventListener listeners : lstnrs) {
				if(listeners.getClass() != listener.getClass()) {
					listener.registerEventHandlers();
					eventListeners.get(plugin).add(listener);
				} else {
					throw new EventException("Preverted registering listener due event fire reasons");
				}
			}
		} else {
			ArrayList<EventListener> lstnrs = new ArrayList<EventListener>();
			listener.registerEventHandlers();
			lstnrs.add(listener);
			eventListeners.put(plugin,lstnrs);
		}
	}
	
	public void unregisterEventListener(Plugin plugin) {
		if(eventListeners.containsKey(plugin)) {
			eventListeners.remove(plugin);
		} else {
			throw new EventException("Plugin is already disabled");
		}
	}
	
	public void unregisterEverything() {
		eventListeners.clear();
	}
	
	/*
	 *
	 * ToDo: ADD EVENT PRIORITY
	 * 
	 */
	
	public void callEvent(Event event) {
		for(Map.Entry<Plugin, ArrayList<EventListener>> entry : eventListeners.entrySet()) {
			for(EventListener listeners : entry.getValue()) {
				if(listeners.getEventHandlers().containsKey(event.getClass())) {
					listeners.getEventHandlers().get(event.getClass()).accept(event);
				}
			}
		}
	}
	
}