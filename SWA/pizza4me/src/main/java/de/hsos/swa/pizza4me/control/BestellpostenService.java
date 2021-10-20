package de.hsos.swa.pizza4me.control;

import de.hsos.swa.pizza4me.entity.Bestellposten;

public interface BestellpostenService {
    
    Bestellposten bestellpostenAnlegen(long kundenId, long bestellungId, long pizzaId, int menge);

    
    Bestellposten bestellpostenAendern(long kundenId, long bestellungId, long bestellpostenId, int menge);

    boolean bestellpostenLoeschen(long kundenId, long bestellungId, long bestellpostenId);
}
