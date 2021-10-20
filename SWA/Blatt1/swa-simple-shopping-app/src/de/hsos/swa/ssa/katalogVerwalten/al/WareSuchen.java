package de.hsos.swa.ssa.katalogVerwalten.al;

import de.hsos.swa.ssa.katalogVerwalten.bl.Katalog;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;
import de.hsos.swa.ssa.katalogVerwalten.dal.WarenRepository;

public class WareSuchen implements SucheWare {

    private Katalog katalog;

    public WareSuchen() {
        this.katalog = new WarenRepository();
    }

    @Override
    public Ware sucheWare(long warennummer) {
        return katalog.suchen(warennummer);
    }
    
}
