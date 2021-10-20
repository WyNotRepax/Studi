package de.hsos.swa.ab06.control;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;

import de.hsos.swa.ab06.entity.Adresse;
import de.hsos.swa.ab06.entity.Kunde;

@RequestScoped
public class KundenService {
    private Map<Long,Kunde> kunden;
    private AtomicLong nextId;
    
    public KundenService() {
        this.kunden = Collections.synchronizedMap(new HashMap<Long,Kunde>());
        this.nextId = new AtomicLong(0);
    }

    public void kundeAnlegen(String vorname, String nachname){
        Long id = nextId.getAndIncrement();
        Kunde kunde = new Kunde(id,vorname, nachname);
        this.kunden.putIfAbsent(id, kunde);
    }

    public Collection<Kunde> kundenAbfragen(){
        return this.kunden.values();
    }

    public Kunde kundeAbfragen(Long kundennummer){
        return this.kunden.get(kundennummer);
    }   


    public boolean kundeLoeschen(Long kundennummer){
        Kunde kunde = this.kunden.remove(kundennummer);
        return kunde != null;
    }

    public void adresseAnlegen(Long kundennummer, Adresse adr){
        Kunde kunde = this.kundeAbfragen(kundennummer);
        if(kunde != null && kunde.getAdresse() == null){
            kunde.setAdresse(adr);
        }
    }

    public void adresseAendern(Long kundennummer, Adresse adr){
        Kunde kunde = this.kundeAbfragen(kundennummer);
        if(kunde != null){
            kunde.setAdresse(adr);
        }
    }

    public Adresse adresseAbfragen(Long kundennummer){
        Kunde kunde = this.kundeAbfragen(kundennummer);
        if(kunde != null){
            return kunde.getAdresse();
        }
        return null;
    }

    public boolean adresseLoeschen(Long kundennummer){
        Kunde kunde = this.kundeAbfragen(kundennummer);
        if(kunde != null && kunde.getAdresse() != null){
            kunde.setAdresse(null);
            return true;
        }
        return false;
    }
}
