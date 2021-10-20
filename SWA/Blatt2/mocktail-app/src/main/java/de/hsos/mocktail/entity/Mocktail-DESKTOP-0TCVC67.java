package de.hsos.mocktail.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * Mocktail
 */
@RegisterForReflection
public class Mocktail {
    private String name;
    private long id;
    private String rezept;
    private String zutaten;
    private String autor;

    public Mocktail() {
    }

    public Mocktail(String name, String rezept, String zutaten, String autor) {
        this();
        this.name = name;
        this.id = -1;
        this.rezept = rezept;
        this.zutaten = zutaten;
        this.autor = autor;
    }

    public String getZutaten() {
        return zutaten;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRezept(String rezept) {
        this.rezept = rezept;
    }

    public void setZutaten(String zutaten) {
        this.zutaten = zutaten;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getName() {
        return this.name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRezept() {
        return this.rezept;
    }

    @Override
    public String toString() {
        return String.format("Mocktail:{name:\"%s\", id:\"%d\", rezept:\"%s\", zutaten:\"%s\", autor:\"%s\"}",
                this.name, this.id, this.rezept, this.zutaten, this.autor);
    }

}