package de.hsos.swa.ssa.suchen.ui.view;

import de.hsos.swa.ssa.suchen.al.EinkaueferIn;
import de.hsos.swa.ssa.suchen.al.WaehleWare;
import de.hsos.swa.ssa.suchen.bl.Ware;

class AuswahlControl {
    
    private final WaehleWare waehleWare;

    AuswahlControl(){
        this.waehleWare = EinkaueferIn.getInstance();
    }

    void wareZuWarenkorbHinzufuegen(Ware ware){
        this.waehleWare.wareZuWarenkorbHinzufuegen(ware);
    }
}
