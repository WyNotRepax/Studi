package de.hsos.swa.ssa.katalogVerwalten.ui.view;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;
import de.hsos.swa.ssa.utils.Geld;
import de.hsos.swa.ssa.utils.InputUtil;

public class AendernView {

    private static final String[] MENU_ITEMS = { "Warennummer anpassen", "Warenname anpassen", "Warenpreis anpassen",
            "Warenbeschreibung anpassen" };
    private final AendernControl control;

    public AendernView() {
        this.control = new AendernControl();
    }

    public Ware display(Ware ware) {
        if(ware == null){
            this.displayNullWareString();
            return ware;
        }
        this.displayWelcomeString();
        this.displayZuAenderndeWare(ware);
        this.displayMenuItems();
        return this.processUserInput(InputUtil.getInt(), ware);
    }

    private void displayWelcomeString() {
        System.out.println("\n - Warendaten anpassen - \n");
    }

    private void displayZuAenderndeWare(Ware ware) {
        System.out.printf("Nummer: %d\nName: %s\nBeschreibung: %s\n\n", ware.getWarennummer(), ware.getName(),
                ware.getBeschreibung());
    }

    private void displayMenuItems() {
        for (int i = 0; i < MENU_ITEMS.length; i++) {
            this.displayMenuItem(i);
        }
    }

    private void displayMenuItem(int i) {
        System.out.printf("%d) %s\n", i + 1, MENU_ITEMS[i]);
    }

    private Ware processUserInput(int index, Ware ware) {
        switch (index) {
        case 1:
            return this.aendernNummer(ware);
        case 2:
            return this.aendernName(ware);
        case 3:
            return this.aendernPreis(ware);
        case 4:
            return this.aendernBeschreibung(ware);
        default:
            this.displayInvalidInputString();
            return ware;
        }
    }


    private Ware aendernBeschreibung( Ware ware) {
        System.out.print("Neue Beschreibung: ");
        String value = InputUtil.getString();
        if (value == null || value.length() == 0) {
            this.displayInvalidInputString();
            return ware;
        }
        Ware wareCopy = new Ware(ware.getWarennummer(), ware.getName(), ware.getPreis(), value);
        if (control.wareAendern(ware, wareCopy)) {
            this.displaySuccessString();
            return wareCopy;
        }
        this.displayFailureString();
        return ware;
    }

    private Ware aendernPreis(Ware ware) {
        System.out.print("Neuer Preis: ");
        Geld value = InputUtil.getGeld();
        if (value == null) {
            this.displayInvalidInputString();
            return ware;
        }
        Ware wareCopy = new Ware(ware.getWarennummer(), ware.getName(), value, ware.getBeschreibung());
        if (control.wareAendern(ware, wareCopy)) {
            this.displaySuccessString();
            return wareCopy;
        }
        this.displayFailureString();
        return ware;
    }

    private Ware aendernName(Ware ware) {
        System.out.print("Neuer Name: ");
        String value = InputUtil.getString();
        if (value == null || value.length() == 0) {
            this.displayInvalidInputString();
            return ware;
        }
        Ware wareCopy = new Ware(ware.getWarennummer(), value, ware.getPreis(), ware.getBeschreibung());
        if (control.wareAendern(ware, wareCopy)) {
            this.displaySuccessString();
            return wareCopy;
        }
        this.displayFailureString();
        return ware;
    }

    private Ware aendernNummer(Ware ware) {
        System.out.print("Neue Warennummer:");
        long value = InputUtil.getLong();
        if (value == -1) {
            this.displayInvalidInputString();
            return ware;
        }
        Ware wareCopy = new Ware(value, ware.getName(), ware.getPreis(), ware.getBeschreibung());
        if (control.wareAendern(ware, wareCopy)) {
            this.displaySuccessString();
            return wareCopy;
        }
        this.displayFailureString();
        return ware;
    }

    private void displayFailureString() {
        System.out.println("Ändern Fehlgeschlagen");
    }

    private void displaySuccessString() {
        System.out.println("Ändern Erfolgreich.");
    }

    private void displayInvalidInputString() {
        System.out.println("Ungültige Eingabe!");
    }
    private void displayNullWareString(){
        System.out.println("Keine Ware zum Ändern vorhanden.");
    }
}
