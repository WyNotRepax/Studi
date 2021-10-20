package controller;

import java.util.ArrayList;
import java.util.List;

import main.Eingabe;
import model.Model;
import view.View;

public class Controller {

    private Model model;
    private List<View> views;


    public Controller() {
    	this.model = new Model(0);
        this.views = new ArrayList<>();
    }

    // Methode dialog darf nicht veraendert werden
    public void dialog() {
        int eingabe = -1;
        while (eingabe != 0) {
            System.out.println("(0) Programm beenden"
                    + "\n(1) neuen View erstellen"
                    + "\n(2) Controller zum Steuern nutzen");
            eingabe = Eingabe.leseInt();
            switch (eingabe) {
                case 1: {
                    neuerView();
                    break;
                }
                case 2: {
                    controllerNutzen();
                    break;
                }
            }
        }
    }

    public void neuerView() {
        System.out.print("Welches Ausgabezeichen? ");
        String zeichen = Eingabe.leseString();
        this.views.add(new View(zeichen.charAt(0),model));
    }

    public void controllerNutzen() {
        System.out.print("neuer Modellwert: ");
        int eingabe = Eingabe.leseInt();
        this.model.setWert(eingabe);
        for(View view: this.views) {
        	view.wertaenderung();
        }
    }
}
