package main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entity.Abteilungswhiteboard;
import entity.Projektwhiteboard;
import entity.Mitarbeiter;

public class Dialog {

	private List<Abteilungswhiteboard> abteilungswhiteboards;
	private List<Projektwhiteboard> projektwhiteboards;
	private List<Mitarbeiter> mitarbeiter;

	public Dialog() {
		this.abteilungswhiteboards = new ArrayList<>();
		this.projektwhiteboards = new ArrayList<>();
		this.mitarbeiter = new ArrayList<>();
	}

	public int auswahl() {
		System.out.print("Was wollen Sie machen?\n" + " (0) Programm beenden\n" + " (1) Mitarbeiter anlegen\n"
				+ " (2) Projektwhiteboard anlegen\n" + " (3) Abteilungswhiteboard anlegen\n"
				+ " (4) Mitarbeiter bei Projektwhiteboard anmelden\n"
				+ " (5) Projekt bei Abteilungswhiteboard anmelden\n" + " (6) Information auf Projektwhiteboard\n"
				+ " (7) Information auf Abteilungswhiteboard\n" + " (8) Uebersicht\n");
		return Eingabe.leseInt();
	}

	public void schleife() {
		int eingabe = -1;
		while (eingabe != 0) {
			eingabe = this.auswahl();
			switch (eingabe) {
			case 1: {
				mitarbeiterHinzufuegen();
				break;
			}
			case 2: {
				projektwhiteboardHinzufuegen();
				break;
			}
			case 3: {
				abteilungswhiteboardHinzufuegen();
				break;
			}
			case 4: {
				mitarbeiterAnmelden();
				break;
			}
			case 5: {
				projektAnmelden();
				break;
			}
			case 6: {
				projektwhiteboardBeschreiben();
				break;
			}
			case 7: {
				abteilungswhiteboardBeschreiben();
				break;
			}
			case 8: {
				uebersichtAnzeigen();
				break;
			}
			}
		}
	}

	public int auswahl(List<?> liste) {
		System.out.println(" (0) Aktion abbrechen");
		for (int i = 0; i < liste.size(); i++) {
			System.out.println(" (" + (i + 1) + ") " + liste.get(i));
		}
		System.out.print("Welches Element: ");
		return Eingabe.leseInt() - 1;
	}

	public void mitarbeiterHinzufuegen() {
		System.out.print("Name: ");
		String name = Eingabe.leseString();
		System.out.print("Mitarbeiternummer: ");
		int minr = Eingabe.leseInt();
		this.mitarbeiter.add(new Mitarbeiter(minr, name));
	}

	public void projektwhiteboardHinzufuegen() {
		System.out.print("Projekt: ");
		String projekt = Eingabe.leseString();
		this.projektwhiteboards.add(new Projektwhiteboard(projekt));

	}

	public void abteilungswhiteboardHinzufuegen() {
		System.out.print("Abteilung: ");
		String abteilung = Eingabe.leseString();
		System.out.print("Abteilungsnummer: ");
		int abtnr = Eingabe.leseInt();
		this.abteilungswhiteboards.add(new Abteilungswhiteboard(abtnr, abteilung));

	}

	public void mitarbeiterAnmelden() {
		System.out.println("Welcher Mitarbeiter?");
		int mitarbeiterI = this.auswahl(this.mitarbeiter);
		Mitarbeiter mitarbeiter = this.mitarbeiter.get(mitarbeiterI);

		System.out.println("Bei welchem Projekt?");
		int projektI = this.auswahl(this.projektwhiteboards);
		Projektwhiteboard projektwhiteboard = this.projektwhiteboards.get(projektI);

		projektwhiteboard.observerHinzufuegen(mitarbeiter);
	}

	public void projektAnmelden() {
		System.out.println("Welches Projekt?");
		int projektI = this.auswahl(this.projektwhiteboards);
		Projektwhiteboard projektwhiteboard = this.projektwhiteboards.get(projektI);

		System.out.println("Bei welcher Abteilung?");
		int abteilungI = this.auswahl(this.abteilungswhiteboards);
		Abteilungswhiteboard abteilungswhiteboard = this.abteilungswhiteboards.get(abteilungI);

		abteilungswhiteboard.observerHinzufuegen(projektwhiteboard);
	}

	public void projektwhiteboardBeschreiben() {
		System.out.println("Welches Projekt?");
		int projektI = this.auswahl(this.projektwhiteboards);
		Projektwhiteboard projektwhiteboard = this.projektwhiteboards.get(projektI);

		System.out.print("Projektnachricht: ");
		String nachricht = Eingabe.leseString();

		projektwhiteboard.neueInformation(nachricht);

	}

	public void abteilungswhiteboardBeschreiben() {
		System.out.println("Welche Abteilung?");
		int abteilungI = this.auswahl(this.abteilungswhiteboards);
		Abteilungswhiteboard abteilungswhiteboard = this.abteilungswhiteboards.get(abteilungI);

		System.out.print("Abteilungsnachricht: ");
		String nachricht = Eingabe.leseString();

		abteilungswhiteboard.neueInformation(nachricht);
	}

	public void uebersichtAnzeigen() {
		System.out.printf("Mitarbeiter: [%s]\n",
				this.mitarbeiter.stream().map(Mitarbeiter::toString).collect(Collectors.joining(",")));
		System.out.printf("Projekte: [%s]\n",
				this.projektwhiteboards.stream().map(Projektwhiteboard::toString).collect(Collectors.joining(",")));
		System.out.printf("Abteilungen: [%s]\n", this.abteilungswhiteboards.stream().map(Abteilungswhiteboard::toString)
				.collect(Collectors.joining(",")));
	}
}