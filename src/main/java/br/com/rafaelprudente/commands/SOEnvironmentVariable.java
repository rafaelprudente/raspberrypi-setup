package br.com.rafaelprudente.commands;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rafaelprudente.bo.Command;
import br.com.rafaelprudente.utils.Functions;

public class SOEnvironmentVariable extends BaseCommand implements ICommand {
	Logger log = LoggerFactory.getLogger(SOEnvironmentVariable.class);

	@Override
	public String commandName() {
		return "ENVIRONMENT VARIABLE";
	}

	@Override
	public void run(Map<String, Object> context, Properties appProperties) throws CommandException {
		log.debug("Start - {}", this.getClass().getName());
		System.out.println("--------------- " + commandName() + " ---------------\n");

		Command command = (Command) context.get("command");
		String osName = Functions.getOSName();
		Process p = null;

		log.debug("OS Name: {}", osName);

		try {
			switch (osName.toUpperCase()) {
			case "UBUNTU":
				if ("ADD".equals(command.getParemeter01()))
					p = Runtime.getRuntime()
							.exec("export " + command.getParemeter02() + "=" + command.getParemeter03());
				if ("REMOVE".equals(command.getParemeter01()))
					p = Runtime.getRuntime().exec("unset " + command.getParemeter02());
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
