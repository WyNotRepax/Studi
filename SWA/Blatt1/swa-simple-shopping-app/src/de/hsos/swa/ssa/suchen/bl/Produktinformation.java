package de.hsos.swa.ssa.suchen.bl;

import java.io.Serializable;

public class Produktinformation {

    private String bezeichnung;
    private Serializable information;

    public Produktinformation(String bezeichnung, Serializable information) {

        this.bezeichnung = bezeichnung;
        this.information = information;

    }

    public String getBezeichnung() {
        return this.bezeichnung;
    }

    public Serializable getInformation() {
        return this.information;
    }

    @Override
    public String toString() {
        return String.format("Produktinformation:{bezeichnung=%s,information=%s}", String.valueOf(this.bezeichnung),
                String.valueOf(this.information));
    }
}
