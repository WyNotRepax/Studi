package de.hsos.swa.ssa.suchen.bl;

import de.hsos.swa.ssa.utils.Geld;

public class Ware {

    private long warennummer;
    private String name;
    private Geld preis;
    private String beschreibung;

    public Ware(long warennummer, String name, Geld preis, String beschreibung) {
        this.warennummer = warennummer;
        this.name = name;
        this.preis = preis;
        this.beschreibung = beschreibung;
    }

    public long getWarennummer() {
        return this.warennummer;
    }

    public String getName() {
        return this.name;
    }

    public Geld getPreis() {
        return this.preis;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }

    @Override
    public String toString() {
        return String.format("Ware:{warennummer=%d,name=%s,preis=%s,beschreibung=%s}", warennummer, name,
                preis.toString(), beschreibung.toString());
    }

}
