package br.com.rafaelprudente.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rafaelprudente.bo.CommandResult;

public class BaseCommand {
	Logger baseLog = LoggerFactory.getLogger(BaseCommand.class);

	protected CommandResult streamHandler(Process p) throws IOException {
		CommandResult commandResult = new CommandResult();
		StringBuilder sb = null;
		String s = null;

		if (p != null) {
			try (InputStreamReader outputStreamReader = new InputStreamReader(p.getInputStream());
					InputStreamReader errorStreamReader = new InputStreamReader(p.getErrorStream())) {
				try (BufferedReader stdInput = new BufferedReader(outputStreamReader);
						BufferedReader stdError = new BufferedReader(errorStreamReader)) {

					// read the output from the command
					sb = new StringBuilder();
					while ((s = stdInput.readLine()) != null) {
						sb.append(s + System.lineSeparator());
					}
					commandResult.setOutput(sb.toString());
					baseLog.debug("\n{}", commandResult.getOutput());

					// read any errors from the attempted command
					sb = new StringBuilder();
					while ((s = stdError.readLine()) != null) {
						sb.append(s + System.lineSeparator());
					}
					commandResult.setError(sb.toString());
					baseLog.debug("\n{}", commandResult.getOutput());

				}
			}
		}

		return commandResult;
	}
}
