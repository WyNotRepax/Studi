package de.hsos.cocktail.entity;

public class Cocktail {
    
    private String name;
    private long id;
    private String rezept;
    private String zutaten;

    public Cocktail(){}

    public Cocktail(String name, long id, String rezept, String zutaten) {

        this.name = name;
        this.id = id;
        this.rezept = rezept;
        this.zutaten = zutaten;

    }

    public String getZutaten() {
        return zutaten;
    }

    public void setZutaten(String zutaten) {
        this.zutaten = zutaten;
    }

    public String getRezept() {
        return rezept;
    }

    public void setRezept(String rezept) {
        this.rezept = rezept;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
