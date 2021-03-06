
public class Zahlenanalyse {
	public static void main(String[] args) {
		zahlenRaten();
	}

	public static boolean istTeilerVon(int t, int z) {
		if (t == 0) {
			return false;
		}
		return (z % t) == 0;
	}

	public static void alleTeilerVon(int z) {
		if (!(z < 1)) {
			int test = 1;
			while (test <= z / 2) {
				if (istTeilerVon(test, z)) {
					IO.println(test);
				}
				test++;
			}
		}
	}

	public static int groessterGemeinsamerTeiler(int a, int b) {
		int ggt = 0;
		for (int i = 1; i < a; i++) {
			if (istTeilerVon(i, a) && istTeilerVon(i, b)) {
				ggt = i;
			}
		}
		return ggt;
	}

	public static void statistiken() {
		double minimum = Double.POSITIVE_INFINITY;
		double maximum = Double.NEGATIVE_INFINITY;
		double schnitt = 0;

		double n = 0;
		while (true) {
			double input = IO.readDouble("Geben Sie einen Wert ein: ");
			if (input == -1) {
				break;
			} else if (input > -100 && input < 100) {
				n++;
				if (input < minimum) {
					minimum = input;
				}
				if (input > maximum) {
					maximum = input;
				}
				schnitt = ((n - 1) * schnitt + input) / n;
				IO.println("Minimum: " + minimum + " Maximum: " + maximum + " Schnitt: " + schnitt);
			}
		}
	}

	public static void zahlenRaten() {
		int n = (int) (98 * Math.random()) + 2;
		// IO.println(n);

		int v = 0;
		while (true) {
			v++;
			int input = IO.readInt("" + v + ". Versuch: Zahl raten: ");
			if (input == n) {
				IO.println("Bravo, getroffen");
				break;
			} else if (input > n) {
				IO.println("zu hoch");
			} else {
				IO.println("zu tief");
			}
		}
	}

}
