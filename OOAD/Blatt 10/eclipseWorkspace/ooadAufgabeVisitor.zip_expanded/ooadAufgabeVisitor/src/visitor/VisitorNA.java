package visitor;

import entity.Feststoff;
import entity.Fluessigkeit;
import interfaces.Visitor;
import interfaces.Zutat;

public class VisitorNA implements Visitor {
	
	// See https://en.wikipedia.org/wiki/Fluid_ounce
	public static final double FLOZ_TO_ML = 29.5735295625;
	public static final double ML_TO_FLOZ = 1.0/FLOZ_TO_ML;
	// See https://en.wikipedia.org/wiki/Ounce#International_avoirdupois_ounce
	public static final double OZ_TO_G = 28.349523125;
	public static final double G_TO_OZ = 1.0/OZ_TO_G;

	@Override
	public String visit(Zutat zutat) {
		if (zutat instanceof Feststoff) {
			return this.visit((Feststoff) zutat);
		}
		if (zutat instanceof Fluessigkeit) {
			return this.visit((Fluessigkeit) zutat);
		}
		System.err.printf("VisitorNA unterstützt nur Feststoffe (%s), und Fluessigkeiten (%s)\n",
				Feststoff.class.getCanonicalName(), Fluessigkeit.class.getCanonicalName());
		return "";
	}
	
	private String visit(Fluessigkeit fluessigkeit) {
		return String.format("%s: %f fl oz",fluessigkeit.getName(),(fluessigkeit.getMl() * ML_TO_FLOZ));

	}

	private String visit(Feststoff feststoff) {
		return String.format("%s: %f oz",feststoff.getName(),(feststoff.getGramm() * G_TO_OZ));
	}

}