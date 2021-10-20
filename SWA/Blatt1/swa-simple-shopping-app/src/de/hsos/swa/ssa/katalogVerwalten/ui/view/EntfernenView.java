package de.hsos.swa.ssa.katalogVerwalten.ui.view;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;
import de.hsos.swa.ssa.utils.InputUtil;

public class EntfernenView {

    private EntfernenControl control;

    public EntfernenView() {
        this.control = new EntfernenControl();
    }

    public Ware display(Ware ware) {
        if (ware == null) {
            this.displayNullWareString();
            return null;
        }
        this.displayWare(ware);
        this.displayWelcomeString();
        if(InputUtil.getBoolean()){
            if(control.wareEntfernen(ware)){
                this.printSuccessString();
                return null;
            }else{
                this.printFailureString();
                return ware;
            }
        }
        return ware;
    }

    private void printFailureString() {
        System.out.println("Ware konnte nicht gelöscht werden.");
    }

    private void printSuccessString() {
        System.out.println("Ware wurde gelöscht.");
    }

    private void displayWelcomeString(){
        System.out.println("Soll diese Ware wirklich entfernt werden? (y/n)");
    }

    private void displayWare(Ware ware) {
        System.out.printf("Warennummer: %d\nName: %s\nPreis: %s\nBeschreibung: %s\n", ware.getWarennummer(), ware.getName(),
                ware.getPreis(), ware.getBeschreibung());
    }

    private void displayNullWareString(){
        System.out.println("Keine Ware zum Löschen vorhanden.");
    }

}
