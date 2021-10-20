package de.hsos.swa.ssa.suchen.ui.view;

import de.hsos.swa.ssa.suchen.al.EinkaueferIn;
import de.hsos.swa.ssa.suchen.al.PruefeWare;
import de.hsos.swa.ssa.suchen.bl.Produktinformation;
import de.hsos.swa.ssa.suchen.bl.Ware;

class PruefControl {
    
    private final PruefeWare pruefeWare;

    public PruefControl(){
        this.pruefeWare = EinkaueferIn.getInstance();
    }

    public Produktinformation[] holeProduktinfromationen(Ware ware){
        return pruefeWare.holeProduktinformationen(ware);
    }

}
