package de.hsos.swa.ab06.entity;

import javax.enterprise.context.Dependent;

@Dependent
public class Kunde {
    private String vorname;
    private String nachname;
    private Long kundennummer;

    private Adresse adresse;


    
    public Kunde() {
    }

    public Kunde(Long kundennummer, String vorname, String nachname) {
        this.kundennummer = kundennummer;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Long getKundennummer() {
        return kundennummer;
    }

    public void setKundennummer(Long kundennummer) {
        this.kundennummer = kundennummer;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    
    
}
