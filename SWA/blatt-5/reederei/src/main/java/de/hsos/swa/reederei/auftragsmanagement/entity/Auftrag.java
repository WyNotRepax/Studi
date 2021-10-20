package de.hsos.swa.reederei.auftragsmanagement.entity;

import java.time.LocalDate;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Auftrag extends PanacheEntity {

    private String auftragsbeschreibung;

    private LocalDate eingangsdatum;

    private String ship;

    public Auftrag() {
        this.ship = null;
    }

    public Auftrag(String auftragsbeschreibung, LocalDate eingangsdatum) {
        this();
        this.auftragsbeschreibung = auftragsbeschreibung;
        this.eingangsdatum = eingangsdatum;
    }

    public String getAuftragsbeschreibung() {
        return auftragsbeschreibung;
    }

    public void setAuftragsbeschreibung(String auftragsbeschreibung) {
        this.auftragsbeschreibung = auftragsbeschreibung;
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }

    public LocalDate getEingangsdatum() {
        return eingangsdatum;
    }

    public void setEingangsdatum(LocalDate eingangsdatum) {
        this.eingangsdatum = eingangsdatum;
    }

}
