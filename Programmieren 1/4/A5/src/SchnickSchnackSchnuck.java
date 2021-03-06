
public class SchnickSchnackSchnuck {
	private static final int SCHERE = 0;
	private static final int STEIN = 1;
	private static final int PAPIER = 2;
	private static final int BRUNNEN = 3;
	private static final String[] STRINGS = { "SCHERE", "STEIN", "PAPIER", "BRUNNEN" };
	private static final int[] CHOOSABLE = { SCHERE, STEIN, PAPIER, BRUNNEN };

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
		int player = input();
		int comp = choose();
		int res = decide(player, comp);
		IO.print("Computer: " + STRINGS[comp] + "; Mensch:" + STRINGS[player] + "; -> ");
		if (res == 1) {
			IO.println("Mensch Gewinnt!");
		} else if (res == 2) {
			IO.println("Computer Gewinnt!");
		} else {
			IO.println("Unentschieden!");
		}
		return res;
	}

	public static int decide(int p1, int p2) {
		int winner = 0;
		if (p1 == SCHERE) {
			if (p2 == SCHERE) {
				winner = 0;
			} else if (p2 == STEIN) {
				winner = 1;
			} else if (p2 == PAPIER) {
				winner = 2;
			} else if (p2 == BRUNNEN) {
				winner = 2;
			}
		} else if (p1 == STEIN) {
			if (p2 == SCHERE) {
				winner = 1;
			} else if (p2 == STEIN) {
				winner = 0;
			} else if (p2 == PAPIER) {
				winner = 2;
			} else if (p2 == BRUNNEN) {
				winner = 2;
			}
		} else if (p1 == PAPIER) {
			if (p2 == SCHERE) {
				winner = 2;
			} else if (p2 == STEIN) {
				winner = 1;
			} else if (p2 == PAPIER) {
				winner = 0;
			} else if (p2 == BRUNNEN) {
				winner = 2;
			}
		} else if (p1 == BRUNNEN) {
			if (p2 == SCHERE) {
				winner = 1;
			} else if (p2 == STEIN) {
				winner = 1;
			} else if (p2 == PAPIER) {
				winner = 2;
			} else if (p2 == BRUNNEN) {
				winner = 0;
			}
		}
		return winner;
	}

	public static int input() {
		String inputString;
		do {
			inputString = IO.readString().toUpperCase();
			IO.println(inputString);

		} while (!(inputString.equals("SCHERE") || inputString.equals("STEIN") || inputString.equals("PAPIER")
				|| inputString.equals("BRUNNEN")));
		if (inputString.equals("SCHERE")) {
			return SCHERE;
		} else if (inputString.equals("STEIN")) {
			return STEIN;
		} else if (inputString.equals("PAPIER")) {
			return PAPIER;
		} else {
			return BRUNNEN;
		}
	}

	public static int choose() {
		return CHOOSABLE[(int) (Math.random() * CHOOSABLE.length)];
	}
}
