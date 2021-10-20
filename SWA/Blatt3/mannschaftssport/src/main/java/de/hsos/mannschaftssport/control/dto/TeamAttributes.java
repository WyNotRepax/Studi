package de.hsos.mannschaftssport.control.dto;

public class TeamAttributes extends Attributes {
    private String name;
    private String category;

    public TeamAttributes(){
        
    }

    public TeamAttributes(String name, String category){
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
}
