package io.commands;


public class ProgrammBeenden implements Command {

	@Override
	public Command execute() {
		System.exit(0);
		return null;
	}
	
	@Override
	public String toString() {
		return "Programm beenden";
	}

}