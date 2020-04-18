package net.novaplay.jbserver.plugin;

import net.novaplay.jbserver.event.*;

public class PluginListener {

    private EventListener listener;

    private EventPriority priority;

    private Plugin plugin;

    private EventExecutor executor;

    private boolean ignoreCancelled;

    public PluginListener(EventListener listener, EventExecutor executor, EventPriority priority, Plugin plugin) {
    	this(listener,executor,priority,plugin,false);
    }
    
    public PluginListener(EventListener listener, EventExecutor executor, EventPriority priority, Plugin plugin, boolean ignoreCancelled) {
        this.listener = listener;
        this.priority = priority;
        this.plugin = plugin;
        this.executor = executor;
        this.ignoreCancelled = ignoreCancelled;
    }

    public EventListener getListener() {
        return listener;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public void callEvent(Event event) {
        if (event instanceof Cancellable) {
            if (event.isCancelled() && isIgnoringCancelled()) {
                return;
            }
        }
        executor.execute(listener, event);
    }

    public void destruct() {

    }

    public boolean isIgnoringCancelled() {
        return ignoreCancelled;
    }
}
