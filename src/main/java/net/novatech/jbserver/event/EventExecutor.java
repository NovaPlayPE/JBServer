package net.novatech.jbserver.event;
public interface EventExecutor {
    void execute(EventListener listener, Event event);
}