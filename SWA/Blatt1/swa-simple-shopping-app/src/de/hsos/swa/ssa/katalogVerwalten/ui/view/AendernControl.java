package de.hsos.swa.ssa.katalogVerwalten.ui.view;

import de.hsos.swa.ssa.katalogVerwalten.al.AendernWare;
import de.hsos.swa.ssa.katalogVerwalten.al.WareAendernEntfernen;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class AendernControl {

    private final AendernWare aendernWare;
    
    public AendernControl() {
        this.aendernWare = new WareAendernEntfernen();
    }

    public boolean wareAendern(Ware oldWare, Ware newWare){
        return this.aendernWare.wareAendern(oldWare, newWare);
    }

}
