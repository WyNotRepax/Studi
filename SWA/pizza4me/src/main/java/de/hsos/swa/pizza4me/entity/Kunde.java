package de.hsos.swa.pizza4me.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
/**
 * @author Chrisoph Freimuth, Benno Steinkamp (pair programming)
 */
@Entity
public class Kunde extends PanacheEntity{

    private Adresse adresse;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Bestellung> bestellungen;

    public Kunde(){
        this.bestellungen = new ArrayList<>();
    }

    public Adresse getAdresse() {
        return this.adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public List<Bestellung> getBestellungen() {
        return bestellungen;
    }

    public void setBestellungen(List<Bestellung> bestellungen) {
        this.bestellungen = bestellungen;
    }
}
