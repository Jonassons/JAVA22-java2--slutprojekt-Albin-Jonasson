package utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLogger {
	private static final Logger logger = LogManager.getLogger(CustomLogger.class);
	private static final List<String> logBuffer = new ArrayList<>();

	private CustomLogger() {
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void log(String logMessage) {
		logger.info(logMessage);
		logBuffer.add(logMessage);
	}

	public static List<String> getLogBuffer() {
		return logBuffer;
	}

}
