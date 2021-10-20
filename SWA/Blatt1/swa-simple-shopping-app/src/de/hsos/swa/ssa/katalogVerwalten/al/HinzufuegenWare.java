package de.hsos.swa.ssa.katalogVerwalten.al;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;
import de.hsos.swa.ssa.utils.Geld;

public interface HinzufuegenWare {
    public Ware wareHinzufuegen(long warennummer, String name, Geld preis, String beschreibung);
}
