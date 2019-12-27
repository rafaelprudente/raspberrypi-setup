package br.com.rafaelprudente.commands;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rafaelprudente.utils.Functions;

public class SOUpgrade extends BaseCommand implements ICommand {
	Logger log = LoggerFactory.getLogger(SOUpgrade.class);

	@Override
	public String commandName() {
		return "UPGRADE";
	}

	@Override
	public void run(Map<String, Object> context, Properties appProperties) throws CommandException {
		log.debug("Start - {}", this.getClass().getName());
		System.out.println("--------------- " + commandName() + " ---------------\n");

		String osName = Functions.getOSName();
		Process p = null;

		log.debug("OS Name: {}", osName);

		try {
			switch (osName.toUpperCase()) {
			case "UBUNTU":
				p = Runtime.getRuntime().exec("apt -yq upgrade");
				break;
			default:
				break;
			}

			streamHandler(p);
		} catch (Exception e) {
			throw new CommandException(e.getMessage(), e);
		} finally {
			log.debug("End - {}", this.getClass().getName());
		}
	}
}