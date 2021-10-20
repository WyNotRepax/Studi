package de.hsos.swa.ssa.suchen.al;

import de.hsos.swa.ssa.suchen.bl.Produktinformation;
import de.hsos.swa.ssa.suchen.bl.Ware;

public interface PruefeWare {
    
    Produktinformation[] holeProduktinformationen(Ware ware);

}
