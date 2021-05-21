package net.novatech.jbserver;

import net.novatech.jbserver.server.Server;
import net.novatech.library.utils.SystemOS;

public class JBMain {

	public final static String API_VERSION = "1.0.0";
	public final static String VERSION = "1.0";
	public final static String PATH = System.getProperty("user.dir") + "/";
	public final static String DATA_PATH = System.getProperty("user.dir") + "/";
	public final static String PLUGIN_PATH = DATA_PATH + "plugins";
	public final static String WORLD_PATH = DATA_PATH + "worlds";
	public static final long START_TIME = System.currentTimeMillis();
	public static boolean ANSI = true;
	public static boolean shortTitle = false;
	public static int DEBUG = 1;

	public static void main(String[] args) {
		if (SystemOS.CURRENT_OS == SystemOS.WINDOWS) {
			shortTitle = true;
		}
		for (String arg : args) {
			switch (arg) {
			case "disable-ansi":
				ANSI = false;
				break;
			}
		}

		try {
			if (ANSI) {
				System.out.print((char) 0x1b + "]0;Starting JBServer for network listening" + (char) 0x07);
			}
			Server server = new Server(PATH, DATA_PATH, PLUGIN_PATH, WORLD_PATH);
		} catch (Exception e) {
		}
	}

}
