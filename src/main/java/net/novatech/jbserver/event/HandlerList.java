package net.novatech.jbserver.event;

import java.util.*;

import net.novatech.jbserver.plugin.Plugin;
import net.novatech.jbserver.plugin.PluginListener;

public class HandlerList {

    private static final ArrayList<HandlerList> allLists = new ArrayList<>();
    private final EnumMap<EventPriority, ArrayList<PluginListener>> handlerslots;
    private volatile PluginListener[] handlers = null;

    public HandlerList() {
        handlerslots = new EnumMap<>(EventPriority.class);
        for (EventPriority o : EventPriority.values()) {
            handlerslots.put(o, new ArrayList<>());
        }
        synchronized (allLists) {
            allLists.add(this);
        }
    }

    public static void bakeAll() {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                h.bake();
            }
        }
    }

    public static void unregisterAll() {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                synchronized (h) {
                    for (List<PluginListener> list : h.handlerslots.values()) {
                        list.clear();
                    }
                    h.handlers = null;
                }
            }
        }
    }

    public static void unregisterAll(Plugin plugin) {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                h.unregister(plugin);
            }
        }
    }

    public static void unregisterAll(EventListener listener) {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                h.unregister(listener);
            }
        }
    }

    public static ArrayList<PluginListener> getPluginListeners(Plugin plugin) {
        ArrayList<PluginListener> listeners = new ArrayList<>();
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                synchronized (h) {
                    for (List<PluginListener> list : h.handlerslots.values()) {
                        for (PluginListener listener : list) {
                            if (listener.getPlugin().equals(plugin)) {
                                listeners.add(listener);
                            }
                        }
                    }
                }
            }
        }
        return listeners;
    }

    public static ArrayList<HandlerList> getHandlerLists() {
        synchronized (allLists) {
            return new ArrayList<>(allLists);
        }
    }

    public synchronized void register(PluginListener listener) {
        if (handlerslots.get(listener.getPriority()).contains(listener))
            throw new IllegalStateException("This listener is already registered to priority " + listener.getPriority().toString());
        handlers = null;
        handlerslots.get(listener.getPriority()).add(listener);
    }

    public void registerAll(Collection<PluginListener> listeners) {
        for (PluginListener listener : listeners) {
            register(listener);
        }
    }

    public synchronized void unregister(PluginListener listener) {
        if (handlerslots.get(listener.getPriority()).remove(listener)) {
            handlers = null;
        }
    }

    public synchronized void unregister(Plugin plugin) {
        boolean changed = false;
        for (List<PluginListener> list : handlerslots.values()) {
            for (ListIterator<PluginListener> i = list.listIterator(); i.hasNext(); ) {
                if (i.next().getPlugin().equals(plugin)) {
                    i.remove();
                    changed = true;
                }
            }
        }
        if (changed) handlers = null;
    }

    public synchronized void unregister(EventListener listener) {
        boolean changed = false;
        for (List<PluginListener> list : handlerslots.values()) {
            for (ListIterator<PluginListener> i = list.listIterator(); i.hasNext(); ) {
                if (i.next().getListener().equals(listener)) {
                    i.remove();
                    changed = true;
                }
            }
        }
        if (changed) handlers = null;
    }

    public synchronized void bake() {
        if (handlers != null) return; // don't re-bake when still valid
        List<PluginListener> entries = new ArrayList<>();
        for (Map.Entry<EventPriority, ArrayList<PluginListener>> entry : handlerslots.entrySet()) {
            entries.addAll(entry.getValue());
        }
        handlers = entries.toArray(new PluginListener[entries.size()]);
    }

    public PluginListener[] getPluginListeners() {
    	PluginListener[] handlers;
        while ((handlers = this.handlers) == null) {
            bake();
        } // This prevents fringe cases of returning null
        return handlers;
    }

}