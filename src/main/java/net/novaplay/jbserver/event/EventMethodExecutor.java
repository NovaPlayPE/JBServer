package net.novaplay.jbserver.event;

import net.novaplay.jbserver.server.Server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class EventMethodExecutor implements EventExecutor {

    private Method method;

    public EventMethodExecutor(Method method) {
        this.method = method;
    }

    @Override
    public void execute(EventListener listener, Event event) {
        try {
            method.invoke(listener, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Server.getInstance().getLogger().logException(e);
        }
    }

    public Method getMethod() {
        return method;
    }
}