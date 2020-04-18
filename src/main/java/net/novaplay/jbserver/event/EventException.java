package net.novaplay.jbserver.event;
import net.novaplay.jbserver.server.ServerException;

public class EventException extends ServerException {
    public EventException(String message) {
        super(message);
    }
}