package de.hsos.swa.ssa.suchen.acl.mock;

import de.hsos.swa.ssa.suchen.acl.WarenkorbFuerSuche;
import de.hsos.swa.ssa.suchen.acl.WarenkorbStaender;

public class WarenkorbStaenderMock implements WarenkorbStaender{
    private final WarenkorbFuerSuche warenkorbFuerSuche;

    public WarenkorbStaenderMock(){
        this.warenkorbFuerSuche = new WarenkorbFuerSucheMock();
    }

    @Override
    public WarenkorbFuerSuche holeWarenkorb(){
        return this.warenkorbFuerSuche;
    }
}
