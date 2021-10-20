package de.hsos.mannschaftssport.control.dto;

public class SelfLinks extends Links{
    private String self;

    public SelfLinks(String self) {
        this.self = self;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    
}
