package br.com.rafaelprudente.bo;

import java.lang.reflect.Field;

public class Command {
	private String commandLine;
	private String commandName;
	private String paremeter01;
	private String paremeter02;
	private String paremeter03;
	private String paremeter04;
	private String paremeter05;
	private String paremeter06;
	private String paremeter07;
	private String paremeter08;
	private String paremeter09;
	private String paremeter10;

	public Command() {

	}

	@SuppressWarnings("deprecation")
	public Command(String commandLine) throws NoSuchFieldException, IllegalAccessException {
		String[] lineCommands = commandLine.split("\\s+");

		if (lineCommands.length > 0)
			this.commandName = lineCommands[0];

		for (int i = 1; i < lineCommands.length; i++) {
			String pId = "00" + i;
			Field f = this.getClass().getDeclaredField("paremeter" + pId.substring(pId.length() - 2));

			boolean accessible = f.isAccessible();
			f.setAccessible(true);
			f.set(this, lineCommands[i].strip());
			f.setAccessible(accessible);
		}
	}

	public String getCommandLine() {
		return commandLine;
	}

	public void setCommandLine(String commandLine) {
		this.commandLine = commandLine;
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public String getParemeter01() {
		return paremeter01;
	}

	public void setParemeter01(String paremeter01) {
		this.paremeter01 = paremeter01;
	}

	public String getParemeter02() {
		return paremeter02;
	}

	public void setParemeter02(String paremeter02) {
		this.paremeter02 = paremeter02;
	}

	public String getParemeter03() {
		return paremeter03;
	}

	public void setParemeter03(String paremeter03) {
		this.paremeter03 = paremeter03;
	}

	public String getParemeter04() {
		return paremeter04;
	}

	public void setParemeter04(String paremeter04) {
		this.paremeter04 = paremeter04;
	}

	public String getParemeter05() {
		return paremeter05;
	}

	public void setParemeter05(String paremeter05) {
		this.paremeter05 = paremeter05;
	}

	public String getParemeter06() {
		return paremeter06;
	}

	public void setParemeter06(String paremeter06) {
		this.paremeter06 = paremeter06;
	}

	public String getParemeter07() {
		return paremeter07;
	}

	public void setParemeter07(String paremeter07) {
		this.paremeter07 = paremeter07;
	}

	public String getParemeter08() {
		return paremeter08;
	}

	public void setParemeter08(String paremeter08) {
		this.paremeter08 = paremeter08;
	}

	public String getParemeter09() {
		return paremeter09;
	}

	public void setParemeter09(String paremeter09) {
		this.paremeter09 = paremeter09;
	}

	public String getParemeter10() {
		return paremeter10;
	}

	public void setParemeter10(String paremeter10) {
		this.paremeter10 = paremeter10;
	}
}
