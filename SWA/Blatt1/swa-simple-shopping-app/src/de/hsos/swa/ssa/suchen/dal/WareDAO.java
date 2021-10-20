package de.hsos.swa.ssa.suchen.dal;

import de.hsos.swa.ssa.suchen.bl.Ware;

public interface WareDAO{

    Ware find(long warennummer);

    Ware[] find(String warenname);

    Ware[] findLike(String warenname);
}