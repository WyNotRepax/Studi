/**
 * Author: Benno Steinkamp
 * Date: 14.12.2020
 */
package kaempfer;

import ausruestung.Ruestung;
import ausruestung.Waffe;

public class Blobb extends Kaempfer {
	private Waffe waffe;

	public Blobb(int gesundheit, int geschick, int sold) {
		super(gesundheit, geschick, sold);
	}

	@Override
	public void nimmWaffe(Waffe w) {
		this.waffe = w;

	}

	@Override
	public void nimmRuestung(Ruestung r) {

	}

	public Waffe getWaffe() {
		return waffe;
	}

	public void setWaffe(Waffe waffe) {
		this.waffe = waffe;
	}

	@Override
	public int kaempfen() {
		System.out.println("Blobb haut zu");
		if(this.waffe == null) {
			return this.getGeschick();
		}
		return waffe.zuhauen(this.getGeschick());
	}

	@Override
	public String toString() {
		return String.format("Blobb (Sold:%d)", this.getSold());
	}

}