package de.hsos.swa.ssa.suchen.al;

import de.hsos.swa.ssa.suchen.bl.Katalog;
import de.hsos.swa.ssa.suchen.bl.Produktinformation;
import de.hsos.swa.ssa.suchen.bl.Ware;
import de.hsos.swa.ssa.suchen.dal.WarenRepository;

public class WarenSuchenUndPruefen {

    private static WarenSuchenUndPruefen instance;

    public static synchronized WarenSuchenUndPruefen getInstance(){
        if(instance == null){
            instance = new WarenSuchenUndPruefen();
        }
        return instance;
    }

    private final Katalog katalog;
    private WarenSuchenUndPruefen(){
        katalog = new WarenRepository();
    }

    public Ware suchen(long warennummer){
        return katalog.suchen(warennummer);
    }

    public Ware[] suchen(String warenname){
        return katalog.suchen(warenname);
    }

    public Produktinformation[] holeProduktinformationen(Ware ware){
        return katalog.holeProduktinformationen(ware);
    }

}
