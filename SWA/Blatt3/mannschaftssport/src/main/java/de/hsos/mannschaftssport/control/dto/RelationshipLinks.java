package de.hsos.mannschaftssport.control.dto;

public class RelationshipLinks extends SelfLinks {

    private String related;

    public RelationshipLinks(String self, String related) {
        super(self);
        this.related = related;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }
    
}
