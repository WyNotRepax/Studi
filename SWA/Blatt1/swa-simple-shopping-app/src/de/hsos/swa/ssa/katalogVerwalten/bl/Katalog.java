package de.hsos.swa.ssa.katalogVerwalten.bl;


public interface Katalog {
    public Ware suchen(long warennummer);
    public boolean wareHinzufügen(Ware ware);
    public boolean wareAeandern(Ware oldWare, Ware newWare);
    public boolean wareEntfernen(Ware ware);
}
