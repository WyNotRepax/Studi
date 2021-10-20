/**
 * Author: Benno Steinkamp
 * Date: 14.12.2020
 */
package kaempfer;

import java.util.Arrays;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import ausruestung.Ruestung;
import ausruestung.Waffe;

/**
 * @author benno
 *
 */
public class Conan extends Kaempfer {

	private Waffe[] waffen;
	private Ruestung ruestung;

	public Conan(int gesundheit, int geschick, int sold) {
		super(gesundheit, geschick, sold);
		this.waffen = new Waffe[2];
	}

	@Override
	public void nimmWaffe(Waffe w) {
		// Waffe 0 nach 1 und neue Waffe in 0
		// So wird immer die älteste Waffe entfernt

		this.waffen[1] = this.waffen[0];
		this.waffen[0] = w;
	}

	@Override
	public void nimmRuestung(Ruestung r) {
		this.ruestung = r;
	}

	public Ruestung getRuestung() {
		return ruestung;
	}

	public void setRuestung(Ruestung ruestung) {
		this.ruestung = ruestung;
	}

	@Override
	public int kaempfen() {
		int ret = 0;
		System.out.println("Conan haut mit links");
		if (this.waffen[0] == null) {
			ret += this.getGeschick();
		} else {
			ret += this.waffen[0].zuhauen(this.getGeschick());
		}
		System.out.println("Conan haut mit rechts");
		if (this.waffen[1] == null) {
			ret += this.getGeschick();
		} else {
			ret += this.waffen[1].zuhauen(this.getGeschick());
		}
		return ret;
	}

	@Override
	public int abwehren(int angriff) {
		if (this.ruestung != null) {
			angriff = ruestung.abwehr(angriff);
		}
		return super.abwehren(angriff);
	}

	@Override
	public String toString() {
		return String.format("Conan (Sold:%d)", this.getSold());
	}

}