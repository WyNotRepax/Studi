package de.hsos.swa.ssa.katalogVerwalten.ui.view;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;
import de.hsos.swa.ssa.utils.InputUtil;


public class VerwaltenStartView {
    private static final String[] MENU_ITEMS = { "Züruck", "Ware suchen", "Ware hinzufügen", "Ware ändern",
            "Ware entfernen" };
    private final VerwaltenStartControl control;

    public VerwaltenStartView() {
        this.control = new VerwaltenStartControl();
    }

    public void display() {
        this.displayWelcomeString();
        do {
            this.displaySuchergebnis();
            this.displayMenuItems();
        } while (this.processUserInput(InputUtil.getInt()));
    }

    private boolean processUserInput(int index) {
        switch (index) {
        case 0:
            return false;
        case 1:
            control.switchToSuchen();
            break;
        case 2:
            control.switchToHinzufuegen();
            break;
        case 3:
            control.switchToAendern();
            break;
        case 4:
            control.switchToEntfernen();
            break;
        default:
            this.displayInvalidInputString();
        }
        return true;
    }

    private void displayWelcomeString() {
        System.out.println("\n - Simple Shopping App | Katalog verwalten -\n");
    }

    private void displayMenuItems() {
        for (int i = 0; i < MENU_ITEMS.length; i++) {
            this.displayMenuItem(i);
        }
    }

    private void displayMenuItem(int i) {
        System.out.printf("%d) %s\n", i, MENU_ITEMS[i]);
    }

    private void displayInvalidInputString() {
        System.out.println("Ungültige Eingabe!");
    }

    private void displaySuchergebnis() {
        Ware suchergebnis = this.control.getSuchergebnis();
        if (suchergebnis != null) {
            System.out.println("\n| Nummer | Name | Beschreibung | Preis |\n");
            System.out.printf("| %d | %s | %s | %.2f%c |\n\n", suchergebnis.getWarennummer(), suchergebnis.getName(),
                    suchergebnis.getBeschreibung(), suchergebnis.getPreis().getConvertedValue(),
                    suchergebnis.getPreis().getWaehrung().displayChar);
        }
    }
}
