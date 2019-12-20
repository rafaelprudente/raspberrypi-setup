package br.com.rafaelprudente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rafaelprudente.commands.CommandHandler;
import br.com.rafaelprudente.utils.ConsoleColors;
import br.com.rafaelprudente.utils.Functions;

@SpringBootApplication
public class RaspberrypiSetupApplication implements CommandLineRunner {
	Logger log = LoggerFactory.getLogger(RaspberrypiSetupApplication.class);
	Properties appProperties = null;

	public static void main(String[] args) {
		SpringApplication.run(RaspberrypiSetupApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("START - Raspberry PI Setup Application");
		try {
			appProperties = Functions.loadProperties();

			for (String command : Functions.loadCommands()) {
				CommandHandler.execute(command);
			}
		} catch (Exception e) {
			Functions.printLnConsole(ConsoleColors.RED, e.getMessage());
			log.error(e.getMessage(), e);
		} finally {
			log.info("END - Raspberry PI Setup Application");
		}
	}
}
