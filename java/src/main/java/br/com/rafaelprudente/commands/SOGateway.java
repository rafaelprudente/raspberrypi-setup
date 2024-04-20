package br.com.rafaelprudente.commands;

import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rafaelprudente.bo.CommandResult;
import br.com.rafaelprudente.utils.Functions;

public class SOGateway extends BaseCommand implements ICommand {
	Logger log = LoggerFactory.getLogger(SOGateway.class);

	@Override
	public String commandName() {
		return "GATEWAY";
	}

	@Override
	public void run(Map<String, Object> context, Properties appProperties) throws CommandException {
		log.debug("Start - {}", this.getClass().getName());
		System.out.println("--------------- " + commandName() + " ---------------\n");

		Pattern ipPattern = Pattern.compile(
				"(1[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
		String osName = Functions.getOSName();
		Process p = null;

		log.debug("OS Name: {}", osName);

		try {
			switch (osName.toUpperCase()) {
			case "UBUNTU":
				p = Runtime.getRuntime().exec("route -n");
				break;
			default:
				break;
			}

			CommandResult commandResult = streamHandler(p);
			for (String line : commandResult.getOutput().split(System.lineSeparator())) {
				if (line.contains(" UG ")) {
					Matcher matcher = ipPattern.matcher(line);
					if (matcher.find()) {
						String gatewayIP = matcher.group(0);
						context.put("gateway", gatewayIP);
						System.out.println("Gateway: " + gatewayIP + "\n");
					}
				}
			}
		} catch (Exception e) {
			throw new CommandException(e.getMessage(), e);
		} finally {
			log.debug("End - {}", this.getClass().getName());
		}
	}
}
