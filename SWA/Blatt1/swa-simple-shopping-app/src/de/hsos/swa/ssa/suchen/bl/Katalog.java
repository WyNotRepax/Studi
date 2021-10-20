package de.hsos.swa.ssa.suchen.bl;

public interface Katalog {
    
    void legeSuchalgorithmusFest(SuchAlgorithmus algo);

    Ware[] suchen(String warenname);

    /*
     * Changed return to single nullable object
     */
    Ware suchen(long warennummer);

    Produktinformation[] holeProduktinformationen(Ware ware);

}
