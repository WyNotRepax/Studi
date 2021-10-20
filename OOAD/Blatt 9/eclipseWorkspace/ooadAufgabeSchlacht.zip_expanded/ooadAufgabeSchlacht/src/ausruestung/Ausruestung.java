/**
 * Author: Benno Steinkamp
 * Date: 14.12.2020
 */
package ausruestung;

public abstract class Ausruestung {
	protected String name;
	protected int preis;

	public Ausruestung(String name, int preis) {
		this.setName(name);
		this.setPreis(preis);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}

	public int getPreis() {
		return preis;
	}
	@Override
	public String toString() {
		return String.format("%s (Preis:%d)", this.getName(),this.getPreis());
	}
}
