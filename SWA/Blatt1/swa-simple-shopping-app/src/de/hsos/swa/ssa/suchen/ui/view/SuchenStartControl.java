package de.hsos.swa.ssa.suchen.ui.view;

import de.hsos.swa.ssa.suchen.bl.Ware;

class SuchenStartControl {
    
    private SuchView suchView;
    private PruefView pruefView;
    private AuswahlView auswahlView;
    private Ware[] suchergebnisse;

    SuchenStartControl(){
        this.suchergebnisse = new Ware[0];
    }


    void switchToSuche() {
        if(this.suchView == null){
            this.suchView = new SuchView();
        }
        this.suchView.display();
        this.suchergebnisse = this.suchView.getSuchergebnis();
    }


    void switchToPruefen() {
        if(this.pruefView == null){
            this.pruefView = new PruefView();
        }
        this.pruefView.display(this.suchergebnisse);
    }


    void switchToAuswahl() {
        if(this.auswahlView == null){
            this.auswahlView = new AuswahlView();
        }
        this.auswahlView.display(this.suchergebnisse);
    }

    public Ware[] getSuchergebnis() {
        return this.suchergebnisse;
    }   
}
