package net.novatech.jbserver.plugin;
import net.novatech.jbserver.server.ServerException;
public class PluginException extends ServerException {
    public PluginException(String message) {
        super(message);
    }
}