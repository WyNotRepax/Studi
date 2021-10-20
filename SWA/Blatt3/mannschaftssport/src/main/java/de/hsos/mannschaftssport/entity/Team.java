package de.hsos.mannschaftssport.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Team
 */
public class Team {

    private long id;
    private Person manager;
    private List<Person> players;
    private String name;
    private String category;

    public Team(long id, Person manager, List<Person> players, String name, String category) {
        this.id = id;
        this.manager = manager;
        if(players == null){
            this.players = new ArrayList<>();
        }else{
            this.players = players;
        }
        this.name = name;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public List<Person> getPlayers() {
        return players;
    }

    public void setPlayers(List<Person> players) {
        this.players = players;
    }

    public String getName() {
        return name;
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