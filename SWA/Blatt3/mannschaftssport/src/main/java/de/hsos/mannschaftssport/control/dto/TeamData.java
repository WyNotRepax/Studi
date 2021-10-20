package de.hsos.mannschaftssport.control.dto;

public class TeamData extends Data {

    private Relationships relationships;

    public TeamData(long id, TeamAttributes attributes, Relationships relationships) {
        super(id, "teams", attributes);
        this.relationships = relationships;
    }

    public Relationships getRelationships() {
        return this.relationships;
    }

    public void setRelationships(Relationships relationships) {
        this.relationships = relationships;
    }

}
