package de.hsos.swa.pizza4me.control;

import java.util.Collection;

import de.hsos.swa.pizza4me.entity.Bestellung;

/**
 * @author Chrisoph Freimuth, Benno Steinkamp (pair programming)
 */
public interface BestellungService {

    Bestellung bestellungAnlegen(Long kundenid);

    Collection<Bestellung> bestellungenAbfragen(Long kundenid);

    Bestellung bestellungAbfragen(long kundenid, long bestellungId);
}
