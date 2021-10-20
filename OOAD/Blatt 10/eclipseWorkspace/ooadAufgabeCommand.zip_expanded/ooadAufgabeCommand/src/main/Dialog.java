package main;

import io.commands.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import business.Rechner;

public class Dialog {

	private Rechner rechner = new Rechner();
	private Map<Integer, Command> aktionen = new HashMap<>();
	private Deque<Command> zuletzt = new ArrayDeque<>();
	private Deque<Command> wieder = new ArrayDeque<>();

	public Dialog() {
		this.aktionen.put(0, new ProgrammBeenden());
		this.aktionen.put(1, new Zuruecksetzen(this.rechner));
		this.aktionen.put(2, new Addieren(this.rechner));
		this.aktionen.put(3, new Subtrahieren(this.rechner));
		this.aktionen.put(4, new AnzeigeSpeichern(this.rechner));
		this.aktionen.put(5, new SpeicherAddieren(this.rechner));
		this.aktionen.put(6, new SpeicherSubtrahieren(this.rechner));
	}

	public void dialog() {
		int eingabe = -1;
		while (eingabe != 0) {
			eingabe = einSchritt();
		}
	}

	public void ausgabe() { // gibt Auswahlmoeglichkeiten aus
		for (int tmp : this.aktionen.keySet()) {
			System.out.println("(" + tmp + ") " + this.aktionen.get(tmp));
		}
		if(this.zuletzt.size() != 0) {
			System.out.println("(98) letzte Aktion rueckgaengig (undo)");
		}
		if(this.wieder.size() != 0) {
			System.out.println("(99) rueckgaengig rueckgaengig machen(redo)");
		}
	}

	public int einSchritt() {
		this.ausgabe();
		int eingabe = Eingabe.leseInt();
		if (eingabe == 98) {
			if(zuletzt.size() == 0) {
				System.out.println("es gibt nichts was rueckgaengig gemacht werden koennte");
			}else {
				wieder.addLast(zuletzt.removeLast().execute());
			}
		} else if (eingabe == 99) {
			if(wieder.size() == 0) {
				System.out.println("es gibt kein Rueckgaengig was rueckgaengig gemacht werden koennte");
			}else {
				zuletzt.addLast(wieder.removeLast().execute());
			}
		} else {
			Command com = this.aktionen.get(eingabe);
			if (com != null) {
				wieder.clear();
				zuletzt.addLast(com.execute());
			}
		}
		System.out.println(this.rechner);
		return eingabe;
	}
}
