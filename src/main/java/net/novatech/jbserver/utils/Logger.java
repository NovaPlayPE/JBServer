package net.novatech.jbserver.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;

import jline.console.ConsoleReader;
import net.novatech.jbserver.JBMain;
import net.novatech.jbserver.server.Server;

public class Logger {

	private ConsoleReader console;
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private org.apache.log4j.Logger apacheLog = null;
	private org.apache.log4j.Logger fileLog = null;
	public static File file = null;
	private static Set<Logger> loggers = new HashSet<>();


	public ConsoleReader getConsole() {
		return this.console;
	}

	public Logger(String path) {
		file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			long date = file.lastModified();
			String logName = new SimpleDateFormat("Y-M-d HH.mm.ss").format(new Date(date)) + ".log";
			File logsPath = new File(JBMain.DATA_PATH, "logs");
			if (!logsPath.exists()) {
				logsPath.mkdirs();
			}
			file.renameTo(new File(logsPath, logName));
			file = new File(path);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		org.apache.log4j.Logger.getRootLogger().setLevel(Level.OFF);
		apacheLog = org.apache.log4j.Logger.getLogger("ApacheLogger");
		fileLog = org.apache.log4j.Logger.getLogger("FileLogger");
		PatternLayout layout = new PatternLayout("[%d{HH:mm:ss}] %m%n");
		ConsoleAppender ap1 = new ConsoleAppender(layout);
		apacheLog.addAppender(ap1);
		try {
			FileAppender f1 = new FileAppender(layout, path, false);
			fileLog.addAppender(f1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		apacheLog.setLevel(Level.INFO);
		fileLog.setLevel(Level.INFO);
		try {
			this.console = new ConsoleReader(System.in, System.out);
			this.console.setExpandEvents(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		loggers.add(this);
	}

	public static Logger getLogger() {
		return Logger.loggers.iterator().next();
	}

	public void error(String message) {
		message = "§c[ERROR]§r " + message + "§r";
		this.fileLog.error(Color.removeColors(message));
		message = Color.colorize(message);
		this.apacheLog.error(message);
	}

	public void info(String message) {
		message = "§b[INFO]§r " + message + "§r";
		this.fileLog.info(Color.removeColors(message));
		message = Color.colorize(message);
		this.apacheLog.info(message);
	}

	public void log(String message) {
		message = "§b[INFO]§r " + message + "§r";
		this.fileLog.info(Color.removeColors(message));
		message = Color.colorize(message);
		this.apacheLog.info(message);
	}

	public void warning(String message) {
		message = "§e[WARNING]§r " + message + "§r";
		this.fileLog.warn(Color.removeColors(message));
		message = Color.colorize(message);
		this.apacheLog.warn(message);
	}

	public void debug(String message) {
		message = "§b[DEBUG]§r " + message + "§r";
		this.fileLog.info(Color.removeColors(message));
		message = Color.colorize(message);
		this.apacheLog.info(message);
	}

	public void logException(Exception message) {
		warning(Utils.getExceptionMessage(message));
	}


}
