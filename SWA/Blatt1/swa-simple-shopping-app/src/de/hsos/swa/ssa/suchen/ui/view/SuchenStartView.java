package de.hsos.swa.ssa.suchen.ui.view;

import de.hsos.swa.ssa.suchen.bl.Ware;
import de.hsos.swa.ssa.utils.InputUtil;

public class SuchenStartView {
    private static final String[] MENU_ITEMS = { "Zurück", "Suchen", "Prüfen", "Auswählen" };

    private final SuchenStartControl control;

    public SuchenStartView() {
        this.control = new SuchenStartControl();
    }

    public void display() {
        this.displayWelcomeString();
        do {
            this.displaySuchergebnisse();
            this.displayMenuItems();
        } while (this.processUserInput(InputUtil.getInt()));

    }

    private boolean processUserInput(int index) {
        switch (index) {
        case 0:
            return false;
        case 1:
            control.switchToSuche();
            break;
        case 2:
            control.switchToPruefen();
            break;
        case 3:
            control.switchToAuswahl();
            break;
        default:
            this.displayInvalidInputString();
        }
        return true;
    }

    private void displayMenuItems() {
        for (int i = 0; i < MENU_ITEMS.length; i++) {
            this.displayMenuItem(i);
        }
    }

    private void displayMenuItem(int i) {
        System.out.printf("%d) %s\n", i, MENU_ITEMS[i]);
    }

    private void displayWelcomeString() {
        System.out.println("\n - Simple Shopping App | Suche -\n");
    }

    private void displayInvalidInputString() {
        System.out.println("Ungültige Eingabe!");
    }

    private void displaySuchergebnisse() {
        Ware[] suchErgebnisse = this.control.getSuchergebnis();
        if (suchErgebnisse.length != 0) {
            this.displaySuchergebnisString(suchErgebnisse.length);
            for (Ware suchergebnis: suchErgebnisse) {
                this.displaySuchergebnis(suchergebnis);
            }
        }
    }

    private void displaySuchergebnisString(int treffer) {
        System.out.printf("%d Treffer bei Ihrer Suche:\n", treffer);
    }

    private void displaySuchergebnis(Ware suchergebnis) {
        System.out.printf("%s | %s | %.2f%c\n\n", suchergebnis.getName(), suchergebnis.getBeschreibung(),
                suchergebnis.getPreis().getConvertedValue(), suchergebnis.getPreis().getWaehrung().displayChar);
    }
}