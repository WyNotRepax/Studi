package de.hsos.swa.reederei.flottenmanagement.entity;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Schiff extends PanacheEntity {
    private boolean frei;
    private String name;

    public Schiff() {
        this.frei = true;
    }

    public Schiff(String name) {
        this();
        this.name = name;
    }

    public boolean isFrei() {
        return frei;
    }

    public void setFrei(boolean frei) {
        this.frei = frei;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
