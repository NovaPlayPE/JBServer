package net.novatech.jbserver.plugin;

import java.io.File;
import java.util.regex.Pattern;

public interface PluginLoader {

    Plugin loadPlugin(String filename) throws Exception;
    Plugin loadPlugin(File file) throws Exception;
    PluginDescription getPluginDescription(String filename);
    PluginDescription getPluginDescription(File file);
    Pattern[] getPluginFilters();
    void enablePlugin(Plugin plugin);
    void disablePlugin(Plugin plugin);

	
}
