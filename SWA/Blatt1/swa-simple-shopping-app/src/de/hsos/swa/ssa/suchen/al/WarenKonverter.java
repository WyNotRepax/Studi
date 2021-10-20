package de.hsos.swa.ssa.suchen.al;

import de.hsos.swa.ssa.suchen.acl.WareDTO;
import de.hsos.swa.ssa.suchen.bl.Ware;
import de.hsos.swa.ssa.utils.Geld;

public class WarenKonverter {

    public WareDTO wareToDto(Ware ware) {
        WareDTO wareDTO = new WareDTO();
        wareDTO.beschreibung = ware.getBeschreibung();
        wareDTO.nummer = ware.getWarennummer();
        wareDTO.name = ware.getName();
        wareDTO.preis = new Geld(ware.getPreis());
        return wareDTO;
    }
}