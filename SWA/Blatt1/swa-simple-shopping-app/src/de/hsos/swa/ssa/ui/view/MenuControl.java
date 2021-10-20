package de.hsos.swa.ssa.ui.view;

import de.hsos.swa.ssa.suchen.ui.view.SuchenStartView;
import de.hsos.swa.ssa.katalogVerwalten.ui.view.VerwaltenStartView;


public class MenuControl {

    private SuchenStartView suchenStartView;
    private VerwaltenStartView verwaltenStartView;

    public MenuControl(){}

    public void switchToSuche(){
        if(suchenStartView == null){
            this.suchenStartView = new SuchenStartView();
        }
        this.suchenStartView.display();
    }

    public void switchToWarenkorb() throws UnsupportedOperationException{
        throw new UnsupportedOperationException(this.displayErrorMsg());
    }

    public void switchToBezahlen() throws UnsupportedOperationException{
        throw new UnsupportedOperationException(this.displayErrorMsg());
    }

    public void switchToKatalogBearbeiten(){
        if(this.verwaltenStartView == null){
            this.verwaltenStartView = new VerwaltenStartView();
        }
        this.verwaltenStartView.display();
    }

    private String displayErrorMsg(){
        String errorMsg = "Not implemented yet!";
        return errorMsg;
    }

}
