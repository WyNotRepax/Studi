package de.hsos.swa.ssa.suchen.al;

import de.hsos.swa.ssa.suchen.bl.Ware;

public interface SucheWare {

    Ware[] sucheWare(String warenname);
    Ware sucheWare(long warennummer);

}
