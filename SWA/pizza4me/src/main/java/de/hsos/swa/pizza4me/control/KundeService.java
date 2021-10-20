package de.hsos.swa.pizza4me.control;

import java.util.Collection;

import de.hsos.swa.pizza4me.entity.Adresse;
import de.hsos.swa.pizza4me.entity.Kunde;
/**
 * @author Chrisoph Freimuth, Benno Steinkamp (pair programming)
 */
public interface KundeService {
    Collection<Kunde> kundenAbfragen();

    Kunde kundenAnlegen();

    void adresseAnlegen(Long kundennummer, Adresse adr);

    void adresseAendern(Long kundennummer, Adresse adr);

    Adresse adresseAbfragen(Long kundennummer);

    boolean adresseLoeschen(Long kundennummer);

    Kunde kundeAbfragen(long id);
}
