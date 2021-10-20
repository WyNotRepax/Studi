package de.hsos.swa.ssa.katalogVerwalten.ui.view;

import de.hsos.swa.ssa.katalogVerwalten.al.SucheWare;
import de.hsos.swa.ssa.katalogVerwalten.al.WareSuchen;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class SuchenControl {
    
    private final SucheWare sucheWare;

    public SuchenControl() {
        this.sucheWare = new WareSuchen();
    }

    public Ware sucheWare(long warennummer) {
        return this.sucheWare.sucheWare(warennummer);
    }

}
