package entity;

public class Mitarbeiter implements Whiteboardobserver {

	private int mitarbeiternummer;
	private String mitarbeitername;

	public Mitarbeiter(int mitarbeiternummer, String mitarbeitername) {
		this.mitarbeiternummer = mitarbeiternummer;
		this.mitarbeitername = mitarbeitername;
	}

	@Override
	public void update(String nachricht) {
		System.out.printf("An %s: %s\n", this.toString(), nachricht);

	}

	@Override
	public String toString() {
		return String.format("%s(%d)", this.mitarbeitername, this.mitarbeiternummer);
	}

}
