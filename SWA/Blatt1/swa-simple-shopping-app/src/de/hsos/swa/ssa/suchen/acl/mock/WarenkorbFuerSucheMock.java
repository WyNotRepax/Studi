package de.hsos.swa.ssa.suchen.acl.mock;

import de.hsos.swa.ssa.suchen.acl.WarenkorbFuerSuche;

import java.util.ArrayList;
import java.util.List;

import de.hsos.swa.ssa.suchen.acl.WareDTO;

public class WarenkorbFuerSucheMock implements WarenkorbFuerSuche {
    private final List<WareDTO> ware;

    WarenkorbFuerSucheMock() {
        this.ware = new ArrayList<>();
    }

    @Override
    public void wareHinzufügen(WareDTO ware) {
        System.out.printf("DEBUG %s: %s zum warenkorb hinzugefügt\n",this.getClass().getCanonicalName(),ware.name);
        this.ware.add(ware);
    }
}
