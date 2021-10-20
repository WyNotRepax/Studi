package view;

import model.Model;

public class View {
	private final char zeichen;
	private final Model model;

	public View(char zeichen, Model model) {
		this.zeichen = zeichen;
		this.model = model;
	}

	public void wertaenderung() {
		int wert = this.model.getWert();
		for (int i = 0; i < wert; i++) {
			System.out.print(this.zeichen);
		}
		System.out.println();
	}
}