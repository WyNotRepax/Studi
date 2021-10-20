package de.hsos.swa.ssa.katalogVerwalten.ui.view;

import de.hsos.swa.ssa.katalogVerwalten.al.EntfernenWare;
import de.hsos.swa.ssa.katalogVerwalten.al.WareAendernEntfernen;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class EntfernenControl {

    private final EntfernenWare entfernenWare;

    EntfernenControl() {
        this.entfernenWare = new WareAendernEntfernen();
    }

    public boolean wareEntfernen(Ware ware) {
        return this.entfernenWare.wareEntfernen(ware);
    }

}
