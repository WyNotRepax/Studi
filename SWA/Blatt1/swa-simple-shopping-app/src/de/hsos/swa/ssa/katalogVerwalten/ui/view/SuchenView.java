package de.hsos.swa.ssa.katalogVerwalten.ui.view;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;
import de.hsos.swa.ssa.utils.InputUtil;

public class SuchenView {

    private final SuchenControl control;

    public SuchenView() {
        this.control = new SuchenControl();
    }

    public Ware display() {
        this.displayWelcomeString();
        this.displayNumberRequestPrompt();
        return this.processUserInput(InputUtil.getLong());
    }

    private Ware processUserInput(long input) {
        if (input <= 0) {
            this.displayInvalidInputString();
            return null;
        }
        return this.control.sucheWare(input);
    }

    private void displayWelcomeString() {
        System.out.println("\n - Suchen Sie nach einer Ware - \n");
    }

    private void displayInvalidInputString() {
        System.out.println("Ungültige Eingabe!");
    }

    private void displayNumberRequestPrompt() {
        System.out.println("Geben Sie eine Warennummer ein:\n");
    }

}
