package de.hsos.swa.ssa.utils;

public class Geld {
    public enum Waehrung {
        Euro('€', 1.0), Dollar('$', 0.83);

        public final char displayChar;
        public final double conversionRate;

        Waehrung(char displayChar, double conversionRate) {
            this.displayChar = displayChar;
            this.conversionRate = conversionRate;
        }

        @Override
        public String toString() {
            return String.valueOf(this.displayChar);
        }
    }

    private double value;
    private Waehrung waehrung;

    public Geld(double value) {
        this(value, Waehrung.Euro);
    }

    public Geld(double value, Waehrung waehrung) {
        this.waehrung = waehrung;
        this.setConvertedValue(value);
    }

    public Geld(Geld geld) {
        this(geld.value, geld.waehrung);
    }

    @Override
    public String toString() {
        return String.format("%.2f%s", this.getConvertedValue(), String.valueOf(this.waehrung));
    }

    public double getValue() {
        return this.value;
    }

    public double getConvertedValue() {
        return this.value * this.waehrung.conversionRate;
    }

    public void setConvertedValue(double convertedValue) {
        this.value = convertedValue / this.waehrung.conversionRate;
    }

    public Waehrung getWaehrung() {
        return this.waehrung;
    }
}
