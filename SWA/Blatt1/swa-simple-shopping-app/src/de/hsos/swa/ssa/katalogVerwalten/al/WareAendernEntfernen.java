package de.hsos.swa.ssa.katalogVerwalten.al;

import de.hsos.swa.ssa.katalogVerwalten.bl.Katalog;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;
import de.hsos.swa.ssa.katalogVerwalten.dal.WarenRepository;

public class WareAendernEntfernen implements AendernWare, EntfernenWare {

    private Katalog katalog;

    public WareAendernEntfernen() {
        this.katalog = new WarenRepository();
    }

    @Override
    public boolean wareEntfernen(Ware ware) {
        return this.katalog.wareEntfernen(ware);

    }

    @Override
    public boolean wareAendern(Ware oldWare, Ware newWare) {
        return this.katalog.wareAeandern(oldWare, newWare);
    }

}
