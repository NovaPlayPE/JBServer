package net.novatech.jbserver.event;

import net.novatech.jbserver.server.Server;

public abstract class Event {

    protected String eventName = null;
    private boolean isCancelled = false;

    final public String getEventName() {
        return eventName == null ? getClass().getName() : eventName;
    }

    public boolean isCancelled() {
        if (!(this instanceof Cancellable)) {
            throw new EventException("Event is not Cancellable");
        }
        return isCancelled;
    }

    public void setCancelled(boolean value) {
        if (!(this instanceof Cancellable)) {
            throw new EventException("Event is not Cancellable");
        }
        isCancelled = value;
    }

    public void setCancelled() {
        setCancelled(true);
    }
    
    public void call() {
    	Server.getInstance().getEventManager().callEvent(this);
    }


}