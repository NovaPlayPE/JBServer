package net.novatech.jbserver.event;
import net.novatech.jbserver.server.ServerException;

public class EventException extends ServerException {
    public EventException(String message) {
        super(message);
    }
}