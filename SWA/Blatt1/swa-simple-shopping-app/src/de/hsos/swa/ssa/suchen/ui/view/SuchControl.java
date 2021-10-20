package de.hsos.swa.ssa.suchen.ui.view;

import de.hsos.swa.ssa.suchen.al.EinkaueferIn;
import de.hsos.swa.ssa.suchen.al.SucheWare;
import de.hsos.swa.ssa.suchen.bl.Ware;

class SuchControl {

    private final SucheWare sucheWare;

    SuchControl(){
        this.sucheWare = EinkaueferIn.getInstance();
    }

    public Ware[] sucheWareWarenname(String warenname) {
        return sucheWare.sucheWare(warenname);
    }

    public Ware sucheWareWarennummer(long warennummer) {
        return sucheWare.sucheWare(warennummer);
    }
}
