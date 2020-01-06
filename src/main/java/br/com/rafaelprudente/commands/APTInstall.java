package br.com.rafaelprudente.commands;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rafaelprudente.bo.Command;
import br.com.rafaelprudente.utils.Functions;

public class APTInstall extends BaseCommand implements ICommand {
	Logger log = LoggerFactory.getLogger(APTInstall.class);

	@Override
	public String commandName() {
		return "APT Install";
	}

	@Override
	public void run(Map<String, Object> context, Properties appProperties) throws CommandException {
		log.debug("Start - {}", this.getClass().getName());
		System.out.println("--------------- " + commandName() + " ---------------\n");

		Command command = (Command) context.get("command");
		String osName = Functions.getOSName();
		Process p = null;

		log.debug("OS Name: {}", osName);
		log.debug("Paremeter 01: {}", command.getParemeter01());

		System.out.println("--------------- " + command.getParemeter01() + " ---------------\n");

		try {
			switch (osName.toUpperCase()) {
			case "UBUNTU":
				p = Runtime.getRuntime().exec("apt -yq install " + command.getParemeter01());
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
