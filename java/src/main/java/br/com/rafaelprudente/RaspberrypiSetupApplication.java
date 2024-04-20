package br.com.rafaelprudente;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rafaelprudente.bo.Command;
import br.com.rafaelprudente.commands.CommandHandler;
import br.com.rafaelprudente.utils.Functions;

@SpringBootApplication
public class RaspberrypiSetupApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(RaspberrypiSetupApplication.class);
	Properties appProperties = null;
	Map<String, Object> context = new HashMap<>();

	public static void main(String[] args) {
		String pattern = "yyyyMMdd-HHmm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		System.setProperty("log.name", simpleDateFormat.format(new Date()));

		SpringApplication.run(RaspberrypiSetupApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("--------------- INICIANDO RASPBERRY PI SETUP ---------------\n");

		try {
			appProperties = Functions.loadProperties();

			for (String command : Functions.loadCommands()) {
				context.put("command", new Command(command));
				CommandHandler.execute(context, appProperties);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			System.out.println("--------------- FINALIZANDO RASPBERRY PI SETUP ---------------\n");
		}
	}
}
