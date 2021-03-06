
public class Main {
	public static void main(String[] args) {
		int width = 6;
		int height = 6;
		Board board = new Board(width, height, ' ', '#', Direction.EAST, 'X');

		board.addCar('X', 3, 2, Direction.EAST, 2);

		board.addCar('O', 0, 0, Direction.SOUTH, 3);
		board.addCar('A', 1, 0, Direction.EAST, 2);
		board.addCar('B', 4, 0, Direction.SOUTH, 2);
		board.addCar('C', 1, 1, Direction.SOUTH, 2);
		board.addCar('D', 2, 1, Direction.SOUTH, 2);
		board.addCar('P', 5, 1, Direction.SOUTH, 3);
		board.addCar('Q', 0, 3, Direction.EAST, 3);
		board.addCar('E', 3, 3, Direction.SOUTH, 2);
		board.addCar('F', 2, 4, Direction.SOUTH, 2);
		board.addCar('G', 4, 4, Direction.EAST, 2);
		board.addCar('H', 0, 5, Direction.EAST, 2);
		board.addCar('I', 3, 5, Direction.EAST, 2);

		do {
			board.render();
			/*
			int x = IO.readInt("Spalte eingeben:");
			while (x < 1 || x > width) {

				board.render();
				x = IO.readInt("Falsche Eingabe! Spalte muss zwischen 1 und " + width + " Liegen!");
			}
			int y = IO.readInt("Zeile eingeben:");
			while (y < 1 || y > height) {

				board.render();
				x = IO.readInt("Falsche Eingabe! Zeile muss zwischen 1 und " + height + " Liegen!");
			}*/
			char c = IO.readChar("Auto Auswaelen: ");
			int direction = -1;
			char directionChar = IO.readChar("Richtung eingeben (l=links, r=rechts, h=hoch, u=runter): ");
			while (directionChar != 'l' && directionChar != 'r' && directionChar != 'h' && directionChar != 'u') {
				directionChar = IO.readChar("Falsche Eingabe! Richtung muss l, r, h oder u sein!");
			}
			switch (directionChar) {
			case 'r':
				direction = Direction.EAST;
				break;
			case 'l':
				direction = Direction.WEST;
				break;
			case 'h':
				direction = Direction.NORTH;
				break;
			case 'u':
				direction = Direction.SOUTH;
				break;
			}
			//board.moveCarAt(x-1, y-1, direction);
			board.moveCarByChar(c, direction);

		} while (!board.won());

	}
}
