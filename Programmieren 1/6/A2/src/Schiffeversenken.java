
public class Schiffeversenken {
	static final int PERCENTAGE_INT = 10;
	static final double PERCENTAGE = PERCENTAGE_INT / 100.0;

	static final int FREE = 0;
	static final int BOAT = 1;
	static final int HIT = 2;
	static final int CHECKED = 3;

	public static void main(String[] args) {
		int[][][] player = new int[2][][];
		int[] playerScore = { 0, 0 };

		int winScore;
		int width;

		do {
			width = IO.readInt("Breite des Feldes: ");
		} while (width <= 0);

		player[0] = init(width);
		player[1] = init(width);

		do {
			IO.println("Es k?nnen " + Math.min(getScore(player[0]), getScore(player[1])) + " Punkte erreicht werden");
			winScore = IO.readInt("Wie viele Punkte bis zum Gewinn?");
		} while (winScore > Math.min(getScore(player[0]), getScore(player[1])));

		int turn = 1;
		int otherTurn = 0;

		while (Math.max(playerScore[0], playerScore[1]) < winScore) {
			otherTurn = turn;
			turn = (turn == 1) ? 0 : 1;

			IO.println("Spieler " + (turn + 1) + " ist dran:");
			printField(player[otherTurn]);

			int y = 0;
			int x = 0;
			do {
				y = IO.readInt("y:") - 1;
				x = IO.readInt("x:") - 1;
			} while (y < 0 || x < 0 || x > width || y > width);

			if (guess(x, y, player[otherTurn])) {
				IO.println("Treffer!");
				playerScore[turn] += x + y + 2;
			}
			IO.println("Spieler " + (turn + 1) + " hat " + playerScore[turn] + " von " + winScore + " Punkte.");
		}

	}

	static int[][] init(int width) {
		int[][] arr = new int[width][width];
		int num = (int) (width * width * PERCENTAGE);
		for (int i = 0; i < num; i++) {
			while (true) {
				int r = (int) (Math.random() * width);
				int c = (int) (Math.random() * width);

				if (placeBoat(arr, r, c)) {
					break;
				}
			}
		}
		return arr;
	}

	static int getScore(int[][] field) {
		int score = 0;
		for (int r = 0; r < field.length; r++) {
			for (int c = 0; c < field.length; c++) {
				if (field[r][c] == BOAT) {
					score += r + c;
				}
			}
		}
		return score;
	}

	static boolean placeBoat(int[][] field, int r, int c) {
		if (r < 0 || r >= field.length || c < 0 || c >= field.length) {
			return false;
		}
		if (field[r][c] != FREE) {
			return false;
		}
		field[r][c] = BOAT;
		return true;

	}

	static boolean guess(int x, int y, int[][] field) {
		if (field[y][x] == BOAT) {
			field[y][x] = HIT;
			return true;
		}
		field[y][x] = CHECKED;
		return false;
	}

	static void printField(int[][] field) {
		IO.print("  |");
		for (int i = 0; i < field.length; i++) {
			IO.print(String.format("%2d |", i + 1));
		}
		IO.println("");
		for (int r = 0; r < field.length; r++) {
			IO.print("--");
			for (int i = 0; i < field.length; i++) {
				IO.print("+---");
			}
			IO.println("+");
			IO.print(String.format("%2d", r + 1));
			for (int c = 0; c < field.length; c++) {
				IO.print("| " + ((field[r][c] == FREE  || field[r][c] == BOAT ) ? " " : field[r][c]) + " ");
			}
			IO.println("|");
		}
		IO.print("--");
		for (int i = 0; i < field.length; i++) {
			IO.print("+---");
		}
		IO.println("+");
	}
}
