package br.com.rafaelprudente.commands;

import java.util.Map;
import java.util.Properties;

public interface ICommand {
	public void run(Map<String, Object> context, Properties appProperties) throws CommandException;

	public String commandName();
}
