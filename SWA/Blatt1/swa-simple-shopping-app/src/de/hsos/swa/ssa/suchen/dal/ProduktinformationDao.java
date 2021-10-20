package de.hsos.swa.ssa.suchen.dal;

import de.hsos.swa.ssa.suchen.bl.Produktinformation;
import de.hsos.swa.ssa.suchen.bl.Ware;

public interface ProduktinformationDao {

    default Produktinformation[] find(Ware ware) {
        return this.find(ware.getWarennummer());
    }

    Produktinformation[] find(Long warennummer);

}