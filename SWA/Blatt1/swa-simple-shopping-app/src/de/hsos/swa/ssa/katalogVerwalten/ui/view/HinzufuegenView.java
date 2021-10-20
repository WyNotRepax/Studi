package de.hsos.swa.ssa.katalogVerwalten.ui.view;


import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;
import de.hsos.swa.ssa.utils.Geld;
import de.hsos.swa.ssa.utils.InputUtil;

public class HinzufuegenView {
    private final HinzufuegenControl control;

    public HinzufuegenView() {
        this.control = new HinzufuegenControl();
    }

    public Ware display() {
        this.displayWelcomeString();
        return this.processUserInput();
    }

    private void displayWelcomeString() {
        System.out.println("\n - Fügen Sie eine Ware zum Katalog hinzu - \n");
    }

    private Ware processUserInput() {

        System.out.print("Nummer: ");
        long nummer = InputUtil.getLong();
        if (nummer == -1) {
            this.displayInvalidInputString();
            return null;
        }

        System.out.print("Name: ");
        String name = InputUtil.getString();
        if (name == null || name.length() == 0) {
            this.displayInvalidInputString();
            return null;
        }

        System.out.print("Preis: ");
        Geld preis = InputUtil.getGeld();
        if (preis == null) {
            this.displayInvalidInputString();
            return null;
        }

        System.out.print("Beschreibung: ");
        String beschreibung = InputUtil.getString();
        if (beschreibung == null || beschreibung.length() == 0) {
            this.displayInvalidInputString();
            return null;
        }

        Ware ware =  this.control.wareHinzufuegen(nummer, name, preis, beschreibung);
        if(ware == null){
            this.displayFailureString();
        }else{
            this.displaySuccessString();
        }
        return ware;
    }

    private void displaySuccessString() {
        System.out.println("Ware erfolgreich hinzugefügt");
    }

    private void displayFailureString() {
        System.out.println("Ware konnte nicht hinzugefügt werden!");
    }

    private void displayInvalidInputString() {
        System.out.println("Ungültige Eingabe!");
    }
}
