package de.hsos.swa.ssa.suchen.ui.view;

import de.hsos.swa.ssa.suchen.bl.Ware;
import de.hsos.swa.ssa.utils.InputUtil;

class SuchView {
    private static final String[] MENU_ITEMS = { "Suchen nach Warenname", "Suchen nach Warennummer" };

    private final SuchControl control;
    private Ware[] suchergebnisse;

    SuchView() {
        this.control = new SuchControl();
        this.suchergebnisse = new Ware[0];
    }

    public void display() {
        this.displayWelcomeString();
        this.displayMenuItems();
        this.processUserInput(InputUtil.getInt());
    }

    public Ware[] getSuchergebnis() {
        return this.suchergebnisse;
    }

    private void processUserInput(int index) {
        switch (index) {
        case 1:
            this.sucheWarenname();
            break;
        case 2:
            this.sucheWarennummer();
            break;
        default:
            this.displayInvalidInputString();
        }
    }

    private void sucheWarennummer() {
        this.displayNumberRequestPrompt();
        long input = InputUtil.getLong();
        if (input <= 0) {
            this.displayInvalidInputString();
            return;
        }
        this.suchergebnisse = new Ware[] { control.sucheWareWarennummer(input) };
    }

    private void sucheWarenname() {
        this.displayNameRequestPrompt();
        String input = InputUtil.getString();
        if (input.length() == 0) {
            this.displayInvalidInputString();
            return;
        }

        this.fillResultArray(input);
    }

    private void fillResultArray(String input) {
        Ware[] ware = this.control.sucheWareWarenname(input);
        int size = ware.length;
        this.suchergebnisse = new Ware[size];

        for (int i = 0; i < this.suchergebnisse.length; i++) {
            this.suchergebnisse[i] = ware[i];
        }
    }

    private void displayWelcomeString() {
        System.out.println("\n - Suchen Sie nach einer Ware - \n");
    }

    private void displayMenuItems() {
        for (int i = 0; i < MENU_ITEMS.length; i++) {
            this.displayMenuItem(i);
        }
    }

    private void displayMenuItem(int i) {
        System.out.printf("%d) %s\n", i + 1, MENU_ITEMS[i]);
    }

    private void displayInvalidInputString() {
        System.out.println("Ungültige Eingabe!");
    }

    private void displayNumberRequestPrompt() {
        System.out.println("Geben Sie eine Warennummer ein:\n");
    }

    private void displayNameRequestPrompt() {
        System.out.println("Geben Sie einen Warennamen ein:\n");
    }
}
