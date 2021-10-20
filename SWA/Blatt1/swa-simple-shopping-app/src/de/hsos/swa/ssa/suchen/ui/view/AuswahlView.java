package de.hsos.swa.ssa.suchen.ui.view;

import de.hsos.swa.ssa.suchen.bl.Ware;
import de.hsos.swa.ssa.utils.InputUtil;

class AuswahlView {

    private final AuswahlControl control;

    public AuswahlView() {
        this.control = new AuswahlControl();

    }

    void display(Ware[] ware) {
        this.displayWelcomeString();
        this.displayWare(ware);
        this.processUserInput(InputUtil.getInt(), ware);
    }

    private void processUserInput(int userInput, Ware[] ware) {
        if (userInput <= 0 || userInput > ware.length) {
            this.displayInvalidInputString();
        } else {
            this.control.wareZuWarenkorbHinzufuegen(ware[userInput - 1]);
        }
    }

    private void displayInvalidInputString() {
        System.out.println("Ungültige Eingabe!");
    }

    private void displayWelcomeString() {
        System.out.println("Ware zum Warenkorb hinzufügen:");
    }

    private void displayWare(Ware[] ware) {
        for (int i = 0; i < ware.length; i++) {
            System.out.printf("%d) | %s | %s | %s |\n\n", i + 1, ware[i].getName(), ware[i].getBeschreibung(),
                    ware[i].getPreis());
        }
    }

}
