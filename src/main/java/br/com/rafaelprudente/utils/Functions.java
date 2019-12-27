package br.com.rafaelprudente.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Functions {
	private static final Logger log = LoggerFactory.getLogger(Functions.class);
	private static final String RASPBERRY_PI_SETUP_PROPERTIES = "raspberry_pi_setup.xml";
	private static final String SETUP_COMMANDS = "setup_commands.txt";
	private static Path applicationStartupFolder;

	static {
		log.debug("Start - Functions");

		try {
			applicationStartupFolder = Path.of(System.getProperty("user.dir"));
			log.debug("applicationStartupFolder: [{}]", applicationStartupFolder);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			log.debug("End - Functions");
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

			log.info("Raspberry PI Setup: {}", propertiesFile);

			if (propertiesFile.toFile().exists()) {
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

	public static String getOSName() {
		String returnValue = "";

		try {
			FilenameFilter ff = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith("-release");
				}
			};

			File file = new File("/etc");
			File[] list = file.listFiles(ff);
			for (File f : list) {
				try (Scanner s = new Scanner(new FileInputStream(f))) {
					while (s.hasNext()) {
						String line = s.nextLine();
						if (line.contains("DISTRIB_ID")) {
							returnValue = line.strip().split("=")[1];
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return returnValue;
	}

	public static void printLnConsole(String color, String message) {
		System.out.println(color + message + "\033[0m");
	}
}
