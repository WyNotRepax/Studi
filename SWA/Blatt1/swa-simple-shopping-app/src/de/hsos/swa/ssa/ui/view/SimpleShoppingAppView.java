package de.hsos.swa.ssa.ui.view;

public class SimpleShoppingAppView {

    private MenuView menuView;

    public static void main(String[] args) {
        new SimpleShoppingAppView().display();
    }

    SimpleShoppingAppView() {
    }

    public void display() {
        if (menuView == null) {
            menuView = new MenuView();
        }
        menuView.display();
    }
}
