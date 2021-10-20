package io.commands;

import helper.language.Messages;
import business.Rechner;

public class AnzeigeSpeichern implements Command {
	private Rechner rechner;

	public AnzeigeSpeichern(Rechner rechner) {
		this.rechner = rechner;
	}

	@Override
	public Command execute() {
		Command ret = new Undo(this.rechner);
		this.rechner.speichern();
		return ret;
	}

	@Override
	public String toString() {
		return Messages.getString("Command.2"); //$NON-NLS-1$
	}

}