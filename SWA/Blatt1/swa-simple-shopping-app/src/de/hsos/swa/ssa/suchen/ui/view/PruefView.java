package de.hsos.swa.ssa.suchen.ui.view;

import de.hsos.swa.ssa.suchen.bl.Produktinformation;
import de.hsos.swa.ssa.suchen.bl.Ware;
import de.hsos.swa.ssa.utils.InputUtil;

class PruefView {

    private final PruefControl control;
    private Produktinformation[] produktInfo;

    PruefView() {
        this.control = new PruefControl();
        this.produktInfo = new Produktinformation[0];
    }

    public void display(Ware[] suchergebnisse) {
        if(suchergebnisse.length != 0) {
            this.displayWelcomeString();
            this.displaySuchergebnisse(suchergebnisse);
            this.processUserInput(InputUtil.getInt(), suchergebnisse);
        }
    }

    private void displayWelcomeString() {
        System.out.println("Für welche ihrer gesuchten Waren wollen Sie zusätzliche Informationen?");
    }

    private void displaySuchergebnisse(Ware[] suchergebnisse) {
        for(int i = 0; i < suchergebnisse.length; i++) {
            System.out.printf("%d. %s\n\n", i + 1, suchergebnisse[i].getName());
        }
    }

    private void processUserInput(int input, Ware[] suchergebnisse) {
        if(input > 0 && input <= suchergebnisse.length) {
            this.produktInfo = this.control.holeProduktinfromationen(suchergebnisse[input - 1]);
            this.displayProduktinformationen(produktInfo);
        } else {
            System.out.println("Ungültige Eingabe!");
        }
    }

    private void displayProduktinformationen(Produktinformation[] produktInfo) {
        for(int i = 0; i < produktInfo.length; i++) {
            System.out.printf("Bezeichnung: %s | Information: %s\n\n", produktInfo[i].getBezeichnung(), produktInfo[i].getInformation());
        }
    }   
}