package de.hsos.swa.ssa.katalogVerwalten.dal;

import de.hsos.swa.ssa.katalogVerwalten.bl.Katalog;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class WarenRepository implements Katalog{

    private WareDAO wareDAO;

    public WarenRepository(){
        this.wareDAO = new WareDAOImpl();
    }

    @Override
    public Ware suchen(long warennummer) {
        return this.wareDAO.find(warennummer);
    }

    @Override
    public boolean wareHinzufügen(Ware ware) {
        return this.wareDAO.create(ware);
    }

    @Override
    public boolean wareAeandern(Ware oldWare, Ware newWare) {
        return this.wareDAO.update(oldWare,newWare);
    }

    @Override
    public boolean wareEntfernen(Ware ware) {
        return this.wareDAO.delete(ware);
    }
    
}
