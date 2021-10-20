package zustand;

import java.util.HashMap;
import java.util.Map;

public class Zustand {
	
	private Map<Character,Zustand> uebergaenge;;
	
	public Zustand() {
		uebergaenge = new HashMap<>();
	}
	
	public Zustand uebergang(char c) {
		return this.uebergaenge.get(c);
	}
	
	public void uebergangHinzufuegen(char zeichen, Zustand zustand) {
		uebergaenge.put(zeichen, zustand);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uebergaenge == null) ? 0 : uebergaenge.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zustand other = (Zustand) obj;
		if (uebergaenge == null) {
			if (other.uebergaenge != null)
				return false;
		} else if (!uebergaenge.equals(other.uebergaenge))
			return false;
		return true;
	}
	
	
}