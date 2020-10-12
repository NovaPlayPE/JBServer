package net.novatech.jbserver.server.settings;

import net.novatech.jbserver.config.Config;

public abstract class BaseSettings {
	
	protected Config config = null;
	
	public BaseSettings(Config config) {
		this.config = config;
	}
	
	public Object getConfig(String variable) {
		return this.getConfig(variable, null);
	}

	public Object getConfig(String variable, Object defaultValue) {
		Object value = this.config.get(variable);
		return value == null ? defaultValue : value;
	}

	public Object getProperty(String variable) {
		return this.getProperty(variable, null);
	}

	public Object getProperty(String variable, Object defaultValue) {
		return this.config.exists(variable) ? this.config.get(variable) : defaultValue;
	}

	public void setPropertyString(String variable, String value) {
		this.config.set(variable, value);
		this.config.save();
	}

	public String getPropertyString(String variable) {
		return this.getPropertyString(variable, null);
	}

	public String getPropertyString(String variable, String defaultValue) {
		return this.config.exists(variable) ? (String) this.config.get(variable) : defaultValue;
	}

	public int getPropertyInt(String variable) {
		return this.getPropertyInt(variable, null);
	}

	public int getPropertyInt(String variable, Integer defaultValue) {
		return this.config.exists(variable) ? (!this.config.get(variable).equals("")
				? Integer.parseInt(String.valueOf(this.config.get(variable)))
				: defaultValue) : defaultValue;
	}

	public void setPropertyInt(String variable, int value) {
		this.config.set(variable, value);
		this.config.save();
	}

	public boolean getPropertyBoolean(String variable) {
		return this.getPropertyBoolean(variable, null);
	}

	public boolean getPropertyBoolean(String variable, Object defaultValue) {
		Object value = this.config.exists(variable) ? this.config.get(variable) : defaultValue;
		if (value instanceof Boolean) {
			return (Boolean) value;
		}
		switch (String.valueOf(value)) {
		case "on":
		case "true":
		case "1":
		case "yes":
			return true;
		}
		return false;
	}

	public void setPropertyBoolean(String variable, boolean value) {
		this.config.set(variable, value ? "1" : "0");
		this.config.save();
	}
	
}