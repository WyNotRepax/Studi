/**
 * Author: Benno Steinkamp
 * Date: 14.12.2020
 */
package ausruestung;

public class Waffe extends Ausruestung {

	private int staerke;

	public Waffe(String name, int staerke, int preis) {
		super(name, preis);
		this.setStaerke(staerke);
	}

	public int zuhauen(int geschick) {
		return this.getStaerke() + geschick;
	}

	public int getStaerke() {
		return staerke;
	}

	public void setStaerke(int staerke) {
		this.staerke = Math.max(0, Math.min(staerke, 10));
	}

	

}
