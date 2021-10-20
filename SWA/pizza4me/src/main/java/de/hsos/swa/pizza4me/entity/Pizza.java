package de.hsos.swa.pizza4me.entity;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
/**
 * @author Chrisoph Freimuth, Benno Steinkamp (pair programming)
 */
@Entity
public class Pizza extends PanacheEntity{

    private String name;
    
    private String beschreibung;

    private String einzelpreis;

    public Pizza() {
    
    
    }

    public Pizza(String name, String beschreibung, String einzelpreis) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.einzelpreis = einzelpreis;
    }



    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getEinzelpreis() {
        return einzelpreis;
    }

    public void setEinzelpreis(String einzelpreis) {
        this.einzelpreis = einzelpreis;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    
}
