package de.hsos.mannschaftssport.control.dto;

public class Relationships {
    private Root manager;
    private Root players;

    public Relationships(Root manager, Root players){
        this.manager = manager;
        this.players = players;
    }

    public Root getManager() {
        return manager;
    }

    public void setManager(Root manager) {
        this.manager = manager;
    }

    public Root getPlayers() {
        return players;
    }

    public void setPlayers(Root players) {
        this.players = players;
    }

    
}
