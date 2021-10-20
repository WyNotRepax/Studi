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
public class Bestellung extends PanacheEntity {
    @OneToMany(fetch = FetchType.EAGER)
    List<Bestellposten> bestellposten;

    public Bestellung(){
        this.bestellposten = new ArrayList<Bestellposten>();
    }

    public List<Bestellposten> getBestellposten() {
        return bestellposten;
    }

    public void setBestellposten(List<Bestellposten> bestellposten) {
        this.bestellposten = bestellposten;
    }
}
