package de.hsos.mannschaftssport.control.dto;

public class Data {
    
    private Long id;
    private String type;
    private Attributes attributes;

    public Data(){}

    protected Data(Long id, String type, Attributes attributes){
        this.id = id;
        this.type = type;
        this.attributes = attributes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    
}
