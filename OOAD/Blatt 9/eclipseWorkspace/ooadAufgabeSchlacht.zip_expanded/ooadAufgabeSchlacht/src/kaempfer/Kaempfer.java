/**
 * Author: Benno Steinkamp
 * Date: 14.12.2020
 */
package kaempfer;

import ausruestung.Ruestung;
import ausruestung.Waffe;

public abstract class Kaempfer {
	protected int gesundheit;
	protected int geschick;
	protected int sold;
	
	public Kaempfer(int gesundheit, int geschick, int sold) {
		this.setGesundheit(gesundheit);
		this.setGeschick(geschick);
		this.setSold(sold);
	}
	
	public abstract int kaempfen();
	
	public int abwehren(int angriff) {
		this.setGesundheit(this.getGesundheit() - angriff);
		return this.getGesundheit();
	}
	
	public abstract void nimmWaffe(Waffe w);
	
	public abstract void nimmRuestung(Ruestung r);

	public int getSold() {
		return sold;
	}

	public int getGesundheit() {
		return gesundheit;
	}

	public int getGeschick() {
		return geschick;
	}

	public void setGeschick(int geschick) {
		// 0 <= geschick <= 10;
		this.geschick = Math.max(0, Math.min(geschick, 10));
	}

	public void setGesundheit(int gesundheit) {
		this.gesundheit = gesundheit;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}
	
	
}