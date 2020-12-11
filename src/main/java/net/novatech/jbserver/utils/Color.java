package net.novatech.jbserver.utils;

import java.util.HashMap;
import java.util.Map;

public class Color {
	public static String BLACK = "\u001b[30m";
    public static String RED = "\u001b[31m";
    public static String GREEN = "\u001b[32m";
    public static String YELLOW = "\u001b[33m";
    public static String BLUE = "\u001b[34m";
    public static String MAGENTA = "\u001b[35m";
    public static String CYAN = "\u001b[36m";
    public static String WHITE = "\u001b[37m";
    
    public static String BOLD = "\u001b[1m";
    public static String RESET = "\u001b[0m";
    public static String RESET_BOLD = "\u001b[21m";
    public static String UNDERLINED = "\u001b[4m";
    
    public static String BLACK_BACKGROUND = "\u001b[38m";
    public static String RED_BACKGROUND = "\u001b[39m";
    public static String YELLOW_BACKGROUND = "\u001b[43m";
    public static String BLUE_BACKGROUND = "\u001b[44m";
    public static String MAGENTA_BACKGROUND = "\u001b[45m";
    public static String CYAN_BACKGROUND = "\u001b[46m";
    public static String WHITE_BACKGROUND = "\u001b[47m";
    
	private static Map<String, String> textFormats = new HashMap<String, String>() {
		{
			this.put("§a", Color.GREEN);
			this.put("§b", Color.CYAN);
			this.put("§c", Color.RED);
			this.put("§d", Color.MAGENTA);
			this.put("§e", Color.YELLOW);
			this.put("§f", Color.WHITE);
			this.put("§0", Color.RESET);
			this.put("§1", Color.BLUE);
			this.put("§2", Color.GREEN);
			this.put("§3", Color.CYAN);
			this.put("§4", Color.RED);
			this.put("§5", Color.MAGENTA);
			this.put("§6", Color.YELLOW);
			this.put("§9", Color.BLUE);
			this.put("§r", Color.RESET);
			this.put("§l", Color.BOLD);
			this.put("§n", Color.UNDERLINED);
		}
	};
	
	private static Map<String, String> textFormatsToMc = new HashMap<String, String>(){
		{
			this.put(Color.GREEN, "§a");
			this.put(Color.CYAN, "§b");
			this.put(Color.RED, "§c");
			this.put(Color.MAGENTA, "§d");
			this.put(Color.YELLOW, "§e");
			this.put(Color.WHITE, "§f");
			this.put(Color.RESET, "§0");
			this.put(Color.BLUE, "§1");
			this.put(Color.GREEN, "§2");
			this.put(Color.CYAN, "§3");
			this.put(Color.RED, "§4");
			this.put(Color.MAGENTA, "§5");
			this.put(Color.YELLOW, "§6");
			this.put(Color.BLUE, "§7");
			this.put(Color.RESET, "§8");
			this.put(Color.BOLD, "§9");
			this.put(Color.UNDERLINED, "§10");
		}
	};
	
	public static String removeColors(String message) {
		String[] list = new String[] { "a", "b", "c", "d", "e", "f", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"r", "l", "n" };
		for (String colors : list) {
			message = message.replaceAll("§" + colors, "");
		}
		return message;
	}

	public static String colorize(String message) {
		for (Map.Entry<String, String> map : textFormats.entrySet()) {
			message = message.replaceAll(map.getKey(), map.getValue());
		}
		return message;
	}
	
	/*
	 * Default Color constants are used in ANSI format
	 * This function will convert ansi format used from plugins to minecraft format
	 * Handling this will be enabled in few packet retranslators
	*/
	public static String colorizeMC(String message) {
		for (Map.Entry<String, String> map : textFormatsToMc.entrySet()) {
			message = message.replaceAll(map.getKey(), map.getValue());
		}
		return message;
	}
	
}
