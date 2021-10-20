package de.hsos.swa.ssa.ui.view;

import de.hsos.swa.ssa.utils.InputUtil;

public class MenuView {
    private static final String[] MENU_ITEMS = { "Exit", "Suchen", "Warenkorb", "Bezahlen", "Katalog Bearbeiten" };

    private final MenuControl control;

    public MenuView() {
        this.control = new MenuControl();
    }

    public void display() {
        this.displayWelcomeString();
        while (true) {
            this.displayMenuItems();
            int selectedIndex = InputUtil.getInt();
            if (selectedIndex == -1) {
                return;
            }
            this.processUserInput(selectedIndex);
        }
    }

    private void processUserInput(int index) {
        switch (index) {
        case 0:
            System.exit(0);
        case 1:
            control.switchToSuche();
            break;
        case 2:
            control.switchToWarenkorb();
            break;
        case 3:
            control.switchToBezahlen();
            break;
        case 4:
            control.switchToKatalogBearbeiten();
            break;
        default:
            this.displayInvalidInputString();
        }
    }

    private void displayMenuItems() {
        for (int i = 0; i < MENU_ITEMS.length; i++) {
            this.displayMenuItem(i);
        }
    }

    private void displayMenuItem(int i) {
        System.out.printf("%d) %s\n", i , MENU_ITEMS[i]);
    }

    private void displayWelcomeString() {
        System.out.println("\n - Herzlich Willkommen bei Ihrer Simple Shopping App - \n");
    }

    private void displayInvalidInputString() {
        System.out.println("Ungültige Eingabe!");
    }
}