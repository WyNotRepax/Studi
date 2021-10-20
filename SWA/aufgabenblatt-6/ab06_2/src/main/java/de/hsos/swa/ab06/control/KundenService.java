package de.hsos.swa.ab06.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;

import de.hsos.swa.ab06.entity.Adresse;
import de.hsos.swa.ab06.entity.Kunde;

public interface KundenService {

    public void kundeAnlegen(String vorname, String nachname);

    public Collection<Kunde> kundenAbfragen();

    public Kunde kundeAbfragen(Long kundennummer);


    public boolean kundeLoeschen(Long kundennummer);

    public void adresseAnlegen(Long kundennummer, Adresse adr);
    

    public void adresseAendern(Long kundennummer, Adresse adr);

    public Adresse adresseAbfragen(Long kundennummer);

    public boolean adresseLoeschen(Long kundennummer);

    public void alleKundenLöschen();
}
