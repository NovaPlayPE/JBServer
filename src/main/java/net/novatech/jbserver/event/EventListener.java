package net.novatech.jbserver.event;

import java.util.*;
import java.util.function.Consumer;

public abstract class EventListener {
	
	private HashMap<Class<? extends Event>, Consumer<Event>> eventHandlers = new HashMap<Class<? extends Event>, Consumer<Event>>();
	
	public abstract void registerEventHandlers();
	
	public void registerEventHandler(Class<? extends Event> clazz, Consumer<Event> consumer) {
		if(!eventHandlers.containsKey(clazz)) {
			eventHandlers.put(clazz, consumer);
		} else {
			throw new EventException("Cannot register same event twice in one listener");
		}
	}
	
	@SuppressWarnings("unused")
	private void registerEventHandler(Class<? extends Event> clazz, EventPriority priority, Consumer<Event> consumer) {
		
	}
	
	public HashMap<Class<? extends Event>, Consumer<Event>> getEventHandlers(){
		return this.eventHandlers;
	}
	
}