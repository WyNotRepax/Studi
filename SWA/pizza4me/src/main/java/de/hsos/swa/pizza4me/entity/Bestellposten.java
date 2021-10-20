package de.hsos.swa.pizza4me.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
/**
 * @author Chrisoph Freimuth, Benno Steinkamp (pair programming)
 */
@Entity
public class Bestellposten extends PanacheEntity {
    @ManyToOne
    private Pizza pizza;

    private int menge;

    public Bestellposten(){

    }

    public Bestellposten(Pizza pizza, int menge){
        this.pizza = pizza;
        this.menge = menge;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }
}
