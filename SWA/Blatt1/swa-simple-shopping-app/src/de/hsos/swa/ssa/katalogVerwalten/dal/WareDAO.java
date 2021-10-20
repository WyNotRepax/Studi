package de.hsos.swa.ssa.katalogVerwalten.dal;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public interface WareDAO{

    Ware find(long warennummer);

    boolean create(Ware ware);

    boolean update(Ware oldWare, Ware newWare);

    boolean delete(Ware ware);
}