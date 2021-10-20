package aktion;

import java.util.Set;

import zustand.Zustand;

public class Automat {
	private Zustand zustand;
	private Set<Zustand> endzustaende;

	public Automat(Zustand start, Set<Zustand> ende) {
		this.zustand = start;
		this.endzustaende = ende;
	}

	public boolean schritt(char ein) {
		if (this.zustand == null) {
			return false;
		}
		System.out.print(zustand + "->");
		this.zustand = zustand.uebergang(ein);
		System.out.println(zustand);
		return zustand != null;
	}

	public boolean imEndzustand() {
		return this.endzustaende.contains(this.zustand);
	}

	@Override
	public String toString() {
		return "Ausfuehrung [zustand=" + zustand + ", endzustaende=" + endzustaende + "]";
	}

}
