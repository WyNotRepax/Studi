package main;

import java.util.HashMap;
import java.util.Map;

public class Verwaltung {

	private Map<Integer, Map<String, Integer>> werte;

	public Verwaltung() {
		werte = new HashMap<>();
	}

	public void start() {
		Ausgabe.printBegruessung();
		int eingabe = -1;
		while (eingabe != 0) {
			Ausgabe.printAuswahl();

			eingabe = Eingabe.leseInt();

			switch (eingabe) {
			case 0:
				break;
			case 1:
				leistungHinzufuegen();
				break;
			case 2:
				leistungAusgeben();
				break;
			default:
				fehlermeldungAusgeben();
			}
		}
	}

	private void fehlermeldungAusgeben() {
		// TODO Fehlermeldung Ausgeben

	}

	private void leistungHinzufuegen() {
		Ausgabe.printMatrikelInput();
		int matnr = Eingabe.leseInt();
		werte.putIfAbsent(matnr, new HashMap<>());
		Ausgabe.printFachInput();
		String fach = Eingabe.leseString();
		Ausgabe.printLpInput();
		int lp = Eingabe.leseInt();
		werte.get(matnr).put(fach, lp);
	}

	private void leistungAusgeben() {
		Ausgabe.printMatrikelInput();
		int matnr = Eingabe.leseInt();
		Map<String, Integer> leistungen = werte.get(matnr);
		int summe = 0;
		if (leistungen != null) {
			leistungen.forEach(Ausgabe::printLpAusgabe);
			summe = leistungen.values().stream().reduce(0, Integer::sum);
			
		}
		Ausgabe.printSumme(summe);
	}
}