// Aufgabe 1
public class BennoSteinkamp {
	
	// Aufgabe 4
	static String student = "Benno Steinkamp";
	
	// Aufgabe 5
	static String[] stringArray = null;

	// Aufgabe 2
	public static void main(String[] args) {

		adventsKalender();

		System.out.println("Array");
		stringArray = init();
		ausgeben(stringArray);
		ausgeben(stringArray, 9);
		ausgeben(reverse(stringArray));
		concat(stringArray);
		ausgeben(stringArray);
		stringArray = reverse(stringArray);
		ausgeben(stringArray);
		// Aufgabe 3
		String teacher = "Heiko Tapken";
		System.out.println(teacher);
	}

	// Aufgabe 6
	public static void adventsKalender() {
		int zufallszahl = (int) (Math.random() * 26 + 1);
		System.out.println("Heute ist der " + zufallszahl + ". Dezember.");
		if (zufallszahl == 6) {
			System.out.println("Alles Gute zum Nikolaus");
		} else if (zufallszahl >= 24 && zufallszahl <= 26) {
			System.out.println("Frohe Weihnachten");
		} else {
			System.out.println("Sch?ne Adventszeit");
		}
	}

	// Aufgabe 7
	public static String[] init() {
		String vorname = "Benno";
		String nachname = "Steinkamp";
		String[] arr = { vorname, nachname };
		return arr;
	}

	// Aufgabe 8
	public static void ausgeben(String[] stringArray) {
		for (int i = 0; i < stringArray.length; i++) {
			System.out.print(stringArray[i] + "\t");
		}
		System.out.println();
	}

	// Aufgabe 9
	public static void ausgeben(String[] stringArray, int n) {
		if (stringArray.length == 2 && n >= 1 && n <= 10) {
			String name = stringArray[0] + " " + stringArray[1];
			do {
				System.out.println(name);
			} while ((int) (Math.random() * 10 + 1) != n);
		}
	}

	// Aufgabe 10
	public static String[] reverse(String[] stringArray) {
		String[] arr = new String[stringArray.length + 1];
		arr[0] = stringArray[stringArray.length - 1];
		for (int i = 0; i < stringArray.length; i++) {
			arr[stringArray.length - i] = stringArray[i];
		}
		return arr;
	}

	// Aufgabe 11
	public static void concat(String[] toConcat) {
		String[] arr = new String[stringArray.length * 2];
		for (int i = 0; i < stringArray.length; i++) {
			arr[i] = stringArray[i];
		}
		for (int i = stringArray.length; i < arr.length; i++) {
			arr[i] = toConcat[toConcat.length - (i - stringArray.length + 1)];
		}
		stringArray = arr;
	}
}
