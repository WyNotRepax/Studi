package io.commands;

import business.Rechner;

public class Zuruecksetzen implements Command {

	private Rechner rechner;
	
	public Zuruecksetzen(Rechner rechner) {
		this.rechner = rechner;
	}
	
	@Override
	public Command execute() {
		Command ret = new Undo(this.rechner);
		rechner.setSpeicher(0);
		rechner.setAnzeige(0);
		return ret;
	}
	
	@Override
	public String toString() {
		return "Rechner zuruecksetzen";
	}

}
