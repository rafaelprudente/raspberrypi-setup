package br.com.rafaelprudente.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Functions {
	private static final String RASPBERRY_PI_SETUP_PROPERTIES = "raspberry_pi_setup.xml";
	private static final String SETUP_COMMANDS = "setup_commands.txt";
	private static final Logger log = LoggerFactory.getLogger(Functions.class);
	private static Path applicationStartupFolder;

	static {
		try {
			applicationStartupFolder = Path.of(Thread.currentThread().getContextClassLoader().getResource("").toURI());
		} catch (URISyntaxException e) {
			log.error(e.getMessage(), e);
		}
	}

	private Functions() {
		throw new IllegalStateException("Functions class");
	}

	public static Properties loadProperties() throws IOException {
		Properties returnValue = new Properties();
		Path propertiesFile = null;
		if (applicationStartupFolder.toFile().exists()) {
			propertiesFile = Paths.get(applicationStartupFolder.toString(), RASPBERRY_PI_SETUP_PROPERTIES);
			if (propertiesFile.toFile().exists()) {
				log.info("Raspberry PI Setup: {}", propertiesFile);
				returnValue.loadFromXML(new FileInputStream(propertiesFile.toString()));
			}
		}

		return returnValue;
	}

	public static List<String> loadCommands() throws IOException {
		List<String> returnValue = new ArrayList<>();
		Path commandsFile = null;
		if (applicationStartupFolder.toFile().exists()) {
			commandsFile = Paths.get(applicationStartupFolder.toString(), SETUP_COMMANDS);
			if (commandsFile.toFile().exists()) {
				log.info("Commands File: {}", commandsFile);

				returnValue = Files.readAllLines(commandsFile);
			}
		}

		return returnValue;
	}

	public static void printLnConsole(String color, String message) {
		System.out.println(color + message + "\033[0m");
	}
}
