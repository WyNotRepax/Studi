package de.hsos.swa.ssa.katalogVerwalten.ui.view;

import de.hsos.swa.ssa.katalogVerwalten.al.HinzufuegenWare;
import de.hsos.swa.ssa.katalogVerwalten.al.WareHinzufuegen;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;
import de.hsos.swa.ssa.utils.Geld;

public class HinzufuegenControl {
    
    private final HinzufuegenWare wareHinzufuegen;

    public HinzufuegenControl() {
        this.wareHinzufuegen = new WareHinzufuegen();
    }

    public Ware wareHinzufuegen(long nummer, String name, Geld preis, String beschreibung) {
        return this.wareHinzufuegen.wareHinzufuegen(nummer, name, preis, beschreibung);
    }

}
