package net.novatech.jbserver.event;

import net.novatech.jbserver.plugin.java.JavaPlugin;
import net.novatech.jbserver.server.Server;

import java.util.ArrayList;

public class EventManager {
	
	private Server server;
	private ArrayList<EventListener> eventListeners = new ArrayList<EventListener>();
	
	public EventManager(Server server) {
		this.server = server;
	}
	
	public void registerEventListener(JavaPlugin plugin, EventListener listener) {
		ArrayList<EventListener> forListnr = new ArrayList<EventListener>(eventListeners);
		if(!plugin.isEnabled()) {
			throw new EventException("Tried to register listener, but plugin is disabled");
		}
		for(EventListener listeners : forListnr) {
			if(listeners.getClass() != listener.getClass()) {
				listener.registerEventHandlers();
				eventListeners.add(listener);
			} else {
				throw new EventException("Preverted registering listener due event fire reasons");
			}
		}
	}
	
	/*
	 *
	 * ToDo: ADD EVENT PRIORITY
	 * 
	 */
	
	public void callEvent(Event event) {
		for(EventListener listeners : eventListeners) {
			if(listeners.getEventHandlers().containsKey(event.getClass())) {
				listeners.getEventHandlers().get(event.getClass()).accept(event);
			}
		}
	}
	
}