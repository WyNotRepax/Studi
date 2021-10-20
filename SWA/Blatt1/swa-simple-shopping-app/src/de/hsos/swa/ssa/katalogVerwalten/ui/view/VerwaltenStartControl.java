package de.hsos.swa.ssa.katalogVerwalten.ui.view;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class VerwaltenStartControl {
    private SuchenView suchenView;
    private HinzufuegenView hinzufuegenView;
    private AendernView aendernView;
    private EntfernenView entfernenView;
    private Ware suchergebnis;

    public VerwaltenStartControl() {}

    public void switchToSuchen() {
        if(this.suchenView == null) {
            this.suchenView = new SuchenView();
        }
        this.suchergebnis = this.suchenView.display();
    }

    public void switchToHinzufuegen() {
        if(this.hinzufuegenView == null) {
            this.hinzufuegenView = new HinzufuegenView();
        }
        this.suchergebnis = this.hinzufuegenView.display();
    }

    public void switchToAendern() {
        if(this.aendernView == null) {
            this.aendernView = new AendernView();
        }
        this.suchergebnis = this.aendernView.display(this.suchergebnis);
    }

    public void switchToEntfernen() {
        if(this.entfernenView == null) {
            this.entfernenView = new EntfernenView();
        }
        this.suchergebnis = this.entfernenView.display(this.suchergebnis);
    }

    public Ware getSuchergebnis() {
        return this.suchergebnis;
    }
}
