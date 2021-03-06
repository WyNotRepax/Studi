
public class SchnickSchnackSchnuckNoArrays {

	public static void main(String[] args) {
		int playerScore = 0;
		int compScore = 0;
		int r = 1;
		while (playerScore < 10 && compScore < 10) {
			IO.println("Spielrunde: " + r);
			int winner = round();
			if (winner == 1) {
				playerScore++;
			} else if (winner == 2) {
				compScore++;
			}
			IO.println("Spielstand: Computer = " + compScore + "; Mensch = " + playerScore);
		}
		IO.print("Spielende ");
		if (playerScore == 10) {
			IO.print("Mensch");
		} else {
			IO.print("Computer");
		}
		IO.println(" hat gewonnen!");
	}

	public static int round() {
		IO.print("Symbol eingeben (Brunnen, Schere, Stein, Papier): ");
		String player = input();
		String comp = choose();
		int res = decide(player, comp);
		IO.print("Computer: " + comp + "; Mensch:" + player + "; -> ");
		if (res == 1) {
			IO.println("Mensch Gewinnt!");
		} else if (res == 2) {
			IO.println("Computer Gewinnt!");
		} else {
			IO.println("Unentschieden!");
		}
		return res;
	}

	public static int decide(String p1, String p2) {
		switch (p1) {
		case "SCHERE":
			switch (p2) {
			case "SCHERE":
				return 0;
			case "STEIN":
				return 2;
			case "PAPIER":
				return 1;
			case "BRUNNEN":
				return 2;
			}
			break;
		case "STEIN":
			switch (p2) {
			case "SCHERE":
				return 1;
			case "STEIN":
				return 0;
			case "PAPIER":
				return 2;
			case "BRUNNEN":
				return 2;
			}
			break;
		case "PAPIER":
			switch (p2) {
			case "SCHERE":
				return 2;
			case "STEIN":
				return 1;
			case "PAPIER":
				return 0;
			case "BRUNNEN":
				return 1;
			}
			break;
		case "BRUNNEN":
			switch (p2) {
			case "SCHERE":
				return 1;
			case "STEIN":
				return 1;
			case "PAPIER":
				return 2;
			case "BRUNNEN":
				return 0;
			}
		}
		return 0;
	}

	public static String input() {
		String inputString;
		do {
			inputString = IO.readString().toUpperCase();
			IO.println(inputString);

		} while (!(inputString.equals("SCHERE") || inputString.equals("STEIN") || inputString.equals("PAPIER")
				|| inputString.equals("BRUNNEN")));
		return inputString;
	}

	public static String choose() {
		switch ((int) (Math.random() * 4)) {
		case 0:
			return "SCHERE";
		case 1:
			return "STEIN";
		case 2:
			return "PAPIER";
		case 3:
			return "BRUNNEN";
		}
		return "";
	}
}
