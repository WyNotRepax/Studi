package de.hsos.swa.ssa.katalogVerwalten.al;

import de.hsos.swa.ssa.katalogVerwalten.bl.Katalog;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;
import de.hsos.swa.ssa.katalogVerwalten.dal.WarenRepository;
import de.hsos.swa.ssa.utils.Geld;

public class WareHinzufuegen implements HinzufuegenWare {

    private Katalog katalog;

    public WareHinzufuegen() {
        this.katalog = new WarenRepository();
    }

    @Override
    public Ware wareHinzufuegen(long warennummer, String name, Geld preis, String beschreibung) {
        Ware ware = new Ware(warennummer, name, preis, beschreibung);
        if(this.katalog.wareHinzufügen(ware)){
            return ware;
        }
        return null;
    }

}
