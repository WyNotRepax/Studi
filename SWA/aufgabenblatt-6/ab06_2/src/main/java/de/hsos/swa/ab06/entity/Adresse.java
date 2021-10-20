package de.hsos.swa.ab06.entity;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Embeddable;

@Vetoed
@Embeddable
public class Adresse {
    private String plz;
    private String ort;
    private String strasse;
    private String hausnummer;

    public Adresse() {
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }
}