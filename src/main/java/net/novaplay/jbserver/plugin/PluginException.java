package net.novaplay.jbserver.plugin;
import net.novaplay.jbserver.server.ServerException;
public class PluginException extends ServerException {
    public PluginException(String message) {
        super(message);
    }
}