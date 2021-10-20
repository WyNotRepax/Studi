package de.hsos.swa.ab06.gateway;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hsos.swa.ab06.control.KundenService;
import de.hsos.swa.ab06.entity.Adresse;
import de.hsos.swa.ab06.entity.Kunde;

@RequestScoped
@Transactional
public class KundenRepository implements KundenService{

    @Inject
    EntityManager em;
    
    public KundenRepository() {
    }

    @Override
    public void kundeAnlegen(String vorname, String nachname){
        Kunde kunde = new Kunde(vorname, nachname);
        this.em.persist(kunde);
    }

    @Override
    public Collection<Kunde> kundenAbfragen(){
        TypedQuery<Kunde> query = this.em.createQuery("from Kunde kunde",Kunde.class);
        return query.getResultList();
    }
    @Override
    public Kunde kundeAbfragen(Long kundennummer){
        return this.em.find(Kunde.class, kundennummer);
    }   
    
    @Override
    public boolean kundeLoeschen(Long kundennummer){

        Kunde kunde = this.kundeAbfragen(kundennummer);
        if(kunde == null){
            return false;
        }
        this.em.remove(kunde);
        return true;
    }

    @Override
    public void adresseAnlegen(Long kundennummer, Adresse adr){
        Kunde kunde = this.kundeAbfragen(kundennummer);
        if(kunde != null && kunde.getAdresse() == null){
            kunde.setAdresse(adr);
            em.merge(kunde);
        }
    }

    @Override
    public void adresseAendern(Long kundennummer, Adresse adr){
        Kunde kunde = this.kundeAbfragen(kundennummer);
        if(kunde != null){
            kunde.setAdresse(adr);
            em.merge(kunde);
        }
    }

    @Override
    public Adresse adresseAbfragen(Long kundennummer){
        Kunde kunde = this.kundeAbfragen(kundennummer);
        if(kunde != null){
            return kunde.getAdresse();
        }
        return null;
    }

    @Override
    public boolean adresseLoeschen(Long kundennummer){
        Kunde kunde = this.kundeAbfragen(kundennummer);
        if(kunde != null && kunde.getAdresse() != null){
            kunde.setAdresse(null);
            em.merge(kunde);
            return true;
        }
        return false;
    }

    @Override
    public void alleKundenLöschen() {
        this.em.createQuery("delete from Kunde kunde").executeUpdate();
    }
}