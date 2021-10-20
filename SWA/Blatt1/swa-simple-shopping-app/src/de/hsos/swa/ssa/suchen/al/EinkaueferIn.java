package de.hsos.swa.ssa.suchen.al;

import de.hsos.swa.ssa.suchen.acl.WarenkorbFuerSuche;
import de.hsos.swa.ssa.suchen.bl.Produktinformation;
import de.hsos.swa.ssa.suchen.bl.Ware;

public class EinkaueferIn implements HoleWarenkorb, SucheWare, PruefeWare, WaehleWare{
    
    private static EinkaueferIn instance;

    private final WarenkorbVerwalten warenkorbVerwalten;

    private EinkaueferIn(){
        this.warenkorbVerwalten = new WarenkorbVerwalten();
    }

    public static synchronized EinkaueferIn getInstance(){
        if(instance == null){
            instance = new EinkaueferIn();
        }
        return instance;
    }

    @Override
    public Produktinformation[] holeProduktinformationen(Ware ware) {
        return WarenSuchenUndPruefen.getInstance().holeProduktinformationen(ware);
    }

    @Override
    public void wareZuWarenkorbHinzufuegen(Ware ware) {
        this.warenkorbVerwalten.wareZuWarenkorbHinzufuegen(ware);
    }

    @Override
    public Ware[] sucheWare(String warenname) {
        return WarenSuchenUndPruefen.getInstance().suchen(warenname);
    }

    @Override
    public Ware sucheWare(long warennummer) {
        return WarenSuchenUndPruefen.getInstance().suchen(warennummer);
    }

    @Override
    public WarenkorbFuerSuche holeWarenkorb() {
        return this.warenkorbVerwalten.holeWarenkorb();
    }

}
