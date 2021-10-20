/**
 * Author: Benno Steinkamp
 * Date: 14.12.2020
 */
package kaempfer;

import ausruestung.Ruestung;
import ausruestung.Waffe;

public class SchwarzerRitter extends Kaempfer {

	private static final int DEFAULT_GESUNDHEIT = 120;
	private static final int DEFAULT_GESCHICK = 0;
	private static final int DEFAULT_SOLD = 0;
	private static final int DEFAULT_SCHADEN = 13;
	private static final int DEFAULT_ABWEHR = 10;

	public SchwarzerRitter() {
		super(DEFAULT_GESUNDHEIT, DEFAULT_GESCHICK, DEFAULT_SOLD);
	}

	@Override
	public void nimmWaffe(Waffe w) {
	}

	@Override
	public void nimmRuestung(Ruestung r) {
	}

	@Override
	public int kaempfen() {
		return DEFAULT_SCHADEN;
	}

	@Override
	public int abwehren(int angriff) {
		System.out.printf("DEBUG: Schwarzer Ritter wird mit %d angegriffen\n",angriff);
		angriff = Math.max(0, angriff - DEFAULT_ABWEHR);
		System.out.printf("DEBUG: Schwarzer Ritter kriegt %d schaden\n",angriff);
		System.out.printf("DEBUG: Schwarzer Ritter hat %d leben\n",this.getGesundheit());
		int ret = super.abwehren(angriff);
		System.out.printf("DEBUG: Schwarzer Ritter hat jetzt noch %d leben\n",ret);
		return ret;
	}
	
	@Override
	public String toString() {
		return "schwarzer Ritter";
	}

}
