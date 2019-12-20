package br.com.rafaelprudente.commands;

import java.lang.reflect.InvocationTargetException;

public class CommandHandler {
	private CommandHandler() {
		throw new IllegalStateException("CommandHandler class");
	}

	public static void execute(String command) throws InstantiationException, IllegalAccessException, CommandException,
			InvocationTargetException, NoSuchMethodException {
		switch (command.strip()) {
		case "SOUpdate":
			SOUpdate.class.getDeclaredConstructor().newInstance().run();
			break;
		case "SOUpgrade":
			SOUpgrade.class.getDeclaredConstructor().newInstance().run();
			break;
		default:
			break;
		}
	}
}
