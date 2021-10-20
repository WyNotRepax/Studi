package de.hsos.swa.ssa.suchen.dal;

import de.hsos.swa.ssa.suchen.bl.Ware;

public class KeywordMatching implements WarenSuche{

    private final WareDAO wareDAO;

    KeywordMatching(WareDAO wareDAO){
        this.wareDAO = wareDAO;
    }

    @Override
    public Ware[] sucheWare(String suchbegriff) {
        return wareDAO.findLike(suchbegriff);
    }
}
