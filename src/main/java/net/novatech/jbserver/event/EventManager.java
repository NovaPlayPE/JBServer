package net.novatech.jbserver.event;

import net.novatech.jbserver.plugin.Plugin;
import net.novatech.jbserver.plugin.java.JavaPlugin;
import net.novatech.jbserver.server.Server;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

public class EventManager {
	
	private Server server;
	private HashMap<Plugin, HashMap<EventListener, HashMap<Class<Event>, Set<Handler>>>> listeners = new HashMap<Plugin, HashMap<EventListener, HashMap<Class<Event>, Set<Handler>>>>();
	
	public EventManager(Server server) {
		this.server = server;
	}
	
	public void registerListener(Plugin plugin, EventListener listener) {
		if(!plugin.isEnabled()) {
			throw new EventException("Tried to register listener, but a plugin is disabled");
		}
		Class<? extends EventListener> clazz = listener.getClass();
		Class<Event> eventClazz = Event.class;
		for(Method m : clazz.getDeclaredMethods()) {
			if(m.getParameterCount() != 1 || !m.isAnnotationPresent(EventHandler.class)) continue;
			Class<?> parameter = m.getParameterTypes()[0];
			if(eventClazz.isAssignableFrom(parameter)) continue;
			Set<Handler> handlers = listeners.get(plugin).get(listener).get(parameter);
			if(handlers == null) {
				handlers = new TreeSet<>(Comparator.comparing(Handler::getPriorityNum));
				HashMap<Class<Event>, Set<Handler>> toPut = new HashMap<Class<Event>, Set<Handler>>();
				toPut.put((Class<Event>)parameter, handlers);;
				this.listeners.put(plugin, new HashMap<EventListener, HashMap<Class<Event>, Set<Handler>>>() {{
					put(listener, toPut);
				}});
			}
			EventHandler annotation = m.getAnnotation(EventHandler.class);
			handlers.add(new Handler(annotation.priority(), annotation.ignoreCancelled(), newConsumer(listener, m)));
		}
	}
	
	private static Consumer<Event> newConsumer(EventListener listener, Method method){
		try {
			MethodHandles.Lookup lookup = constructLookup(listener.getClass());
			Consumer<Event> consumer = (Consumer<Event>) LambdaMetafactory.metafactory(
					lookup,
					"accept",
					MethodType.methodType(Consumer.class, listener.getClass()),
					MethodType.methodType(void.class, Object.class),
					lookup.unreflect(method),
					MethodType.methodType(void.class, method.getParameterTypes())).getTarget().invoke(listener);
			return consumer;
		}catch(Throwable t) { return null;}
	}
	
	private static MethodHandles.Lookup constructLookup(Class<?> owner) throws Exception{
		Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class);
		constructor.setAccessible(true);
		try {
			return constructor.newInstance(owner);
		}finally {
			constructor.setAccessible(false);
		}
	}
	
	public void unregisterListeners(Plugin plugin) {
		if(listeners.containsKey(plugin)) {
			listeners.remove(plugin);
		}
	}
	
	public void unregisterEverything() {
		listeners.clear();
	}
	
	public void callEvent(Event event) {
		for(HashMap<EventListener,HashMap<Class<Event>, Set<Handler>>> handlerRegistry : listeners.values()) {
			for(HashMap<Class<Event>, Set<Handler>> map : handlerRegistry.values()) {
				Set<Handler> handlers = map.get(event.getClass());
				if(handlers == null) return;
				Cancellable ev = event instanceof Cancellable ? (Cancellable)event : null;
				for(Handler h : handlers) {
					if(h.ignoreCancelled && ev != null && ev.isCancelled()) continue;
					h.consumer.accept(event);
				}
			}
		}
	}
	
	private static class Handler {
		
		private final EventPriority priority;
		private final boolean ignoreCancelled;
		private final Consumer<Event> consumer;
		
		Handler(EventPriority priority, boolean ignoreCancelled, Consumer<Event> consumer){
			this.priority = priority;
			this.ignoreCancelled = ignoreCancelled;
			this.consumer = consumer;
		}
		
		private EventPriority priority() {
			return this.priority;
		}
		
		/*
		 * In order to avoid lambda exceptions
		 */
		private int getPriorityNum() {
			return priority().getSlot();
		}
	}
	
}