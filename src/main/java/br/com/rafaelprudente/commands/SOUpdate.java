package br.com.rafaelprudente.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SOUpdate implements ICommand {

	@Override
	public void run() throws CommandException {
		try {
			String s = null;
			Process p = Runtime.getRuntime().exec("sudo apt update");

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
		} catch (Exception e) {
			throw new CommandException(e.getMessage(), e);
		}
	}
}
