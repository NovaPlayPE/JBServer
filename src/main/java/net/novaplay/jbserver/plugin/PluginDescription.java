package net.novaplay.jbserver.plugin;

import java.util.*;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class PluginDescription {

	private String name;
    private String main;
    private boolean signed;
    private List<String> api;
    private List<String> depend = new ArrayList<>();
    private List<String> softDepend = new ArrayList<>();
    private List<String> loadBefore = new ArrayList<>();
    private String version;
    private Map<String, Object> commands = new HashMap<>();
    private String description;
    private List<String> authors = new ArrayList<>();
    private String website;
    private String prefix;

    public PluginDescription(Map<String, Object> yamlMap) {
        this.loadMap(yamlMap);
    }

    public PluginDescription(String yamlString) {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(dumperOptions);
        this.loadMap(yaml.loadAs(yamlString, LinkedHashMap.class));
    }

    private void loadMap(Map<String, Object> plugin) throws PluginException {
        this.name = ((String) plugin.get("name")).replaceAll("[^A-Za-z0-9 _.-]", "");
        if (this.name.equals("")) {
            throw new PluginException("Invalid PluginDescription name");
        }
        this.name = this.name.replace(" ", "_");
        this.version = (String) plugin.get("version");
        this.main = (String) plugin.get("main");
        Object api = plugin.get("api");
        if (api instanceof List) {
            this.api = (List<String>) api;
        } else {
            List<String> list = new ArrayList<>();
            list.add((String) api);
            this.api = list;
        }
        if (this.main.startsWith("net.novaplay.jbproxy.")) {
            throw new PluginException("Invalid PluginDescription main, cannot start within the net.novaplay.jbproxy. package");
        }

        if (plugin.containsKey("code_signing")) {
            this.signed = Boolean.valueOf((String) plugin.get("code_signing"));
        }

        if (plugin.containsKey("commands") && plugin.get("commands") instanceof Map) {
            this.commands = (Map<String, Object>) plugin.get("commands");
        }

        if (plugin.containsKey("depend")) {
            this.depend = (List<String>) plugin.get("depend");
        }

        if (plugin.containsKey("softdepend")) {
            this.softDepend = (List<String>) plugin.get("softdepend");
        }

        if (plugin.containsKey("loadbefore")) {
            this.loadBefore = (List<String>) plugin.get("loadbefore");
        }

        if (plugin.containsKey("website")) {
            this.website = (String) plugin.get("website");
        }

        if (plugin.containsKey("description")) {
            this.description = (String) plugin.get("description");
        }

        if (plugin.containsKey("prefix")) {
            this.prefix = (String) plugin.get("prefix");
        }

        if (plugin.containsKey("author")) {
            this.authors.add((String) plugin.get("author"));
        }

        if (plugin.containsKey("authors")) {
            this.authors.addAll((Collection<? extends String>) plugin.get("authors"));
        }
    }

    public String getFullName() {
        return this.name + " v" + this.version;
    }

    public List<String> getCompatibleAPIs() {
        return api;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getPrefix() {
        return prefix;
    }

    public Map<String, Object> getCommands() {
        return commands;
    }

    public List<String> getDepend() {
        return depend;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getLoadBefore() {
        return loadBefore;
    }

    public String getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public List<String> getSoftDepend() {
        return softDepend;
    }

    public boolean isSigned() {
        return signed;
    }

    public String getVersion() {
        return version;
    }

    public String getWebsite() {
        return website;
    }
	
}
