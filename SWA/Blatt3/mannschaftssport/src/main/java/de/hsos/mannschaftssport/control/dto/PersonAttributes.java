package de.hsos.mannschaftssport.control.dto;

public class PersonAttributes extends Attributes {
    private String name;

    public PersonAttributes(){}

    public PersonAttributes(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
