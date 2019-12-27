package br.com.rafaelprudente.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;

import br.com.rafaelprudente.bo.Command;

public class CommandHandler {
	private CommandHandler() {
		throw new IllegalStateException("CommandHandler class");
	}

	public static void execute(Map<String, Object> context, Properties appProperties) throws InstantiationException,
			IllegalAccessException, CommandException, InvocationTargetException, NoSuchMethodException {

		Command command = (Command) context.get("command");

		switch (command.getCommandName()) {
		case "SOUpdate":
			SOUpdate.class.getDeclaredConstructor().newInstance().run(context, appProperties);
			break;
		case "SOUpgrade":
			SOUpgrade.class.getDeclaredConstructor().newInstance().run(context, appProperties);
			break;
		case "SOGateway":
			SOGateway.class.getDeclaredConstructor().newInstance().run(context, appProperties);
			break;
		case "APTInstall":
			APTInstall.class.getDeclaredConstructor().newInstance().run(context, appProperties);
			break;
		default:
			break;
		}
	}
}
