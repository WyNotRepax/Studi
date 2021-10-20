package de.hsos.swa.ssa.suchen.al;

import de.hsos.swa.ssa.suchen.acl.WarenkorbFuerSuche;
import de.hsos.swa.ssa.suchen.acl.WarenkorbStaender;
import de.hsos.swa.ssa.suchen.acl.mock.WarenkorbStaenderMock;
import de.hsos.swa.ssa.suchen.bl.Ware;

public class WarenkorbVerwalten {
    
    private WarenKonverter konverter;
    private WarenkorbStaender warenkorbStaender;

    WarenkorbVerwalten(){
        this.konverter = new WarenKonverter();
        this.warenkorbStaender = new WarenkorbStaenderMock();
    }

    public void wareZuWarenkorbHinzufuegen(Ware ware){
        this.warenkorbStaender.holeWarenkorb().wareHinzufügen(konverter.wareToDto(ware));
    }

    public WarenkorbFuerSuche holeWarenkorb(){
        return this.warenkorbStaender.holeWarenkorb();
    }
}
