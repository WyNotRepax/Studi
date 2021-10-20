package de.hsos.mannschaftssport.gateway;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import de.hsos.mannschaftssport.acl.PassFuerMannschaftssport;
import de.hsos.mannschaftssport.acl.PassManagement;
import de.hsos.mannschaftssport.entity.Person;
import de.hsos.mannschaftssport.entity.PersonCollection;
import de.hsos.mannschaftssport.entity.Team;
import de.hsos.mannschaftssport.entity.TeamCollection;

/**
 * RepoMock
 */
@ApplicationScoped
public class RepoMock implements TeamCollection, PersonCollection, PassManagement {

    private int nextId = 0;
    private int nextTeamId = 0;

    private Map<Long, Team> teams;
    private Map<Long, Person> persons;

    public RepoMock() {

        this.teams = Collections.synchronizedMap(new HashMap<>());
        this.persons = Collections.synchronizedMap(new HashMap<>());

        this.initMockData();
    }

    private void initMockData() {
        Person[] testPersons = new Person[15];
        for (int i = 0; i < testPersons.length; i++) {
            testPersons[i] = new Person(nextId, String.format("Person %d", nextId++));
            createPerson(testPersons[i]);
        }
        Person testManager = new Person(nextId++, "TestManager");
        createPerson(testManager);
        Team testTeam = new Team(-1,testManager,null,"TestTeam","TestKategorie");
        testTeam.getPlayers().addAll(Arrays.asList(testPersons));
        createTeam(testTeam);
    }

    @Override
    public List<Person> findAllPersons(String nameFilter, int page, int pagesize) {
        return this.persons.values().stream().filter(person -> person.getName().contains(nameFilter))
                .skip((page - 1) * pagesize).limit(pagesize).collect(Collectors.toList());
    }

    @Override
    public Person findByIdPerson(long id) {
        return this.persons.get(id);
    }

    @Override
    public boolean createPerson(Person person) {
        if (this.persons.containsKey(person.getId())) {
            return false;
        }
        this.persons.put(person.getId(), person);
        return true;
    }

    @Override
    public boolean changePerson(Person person) {
        if (!this.persons.containsKey(person.getId())) {
            return false;
        }
        this.persons.put(person.getId(), person);
        return true;
    }

    @Override
    public boolean deletePerson(long id) {
        if (!this.persons.containsKey(id)) {
            return false;
        }
        this.persons.remove(id);
        return true;
    }

    @Override
    public List<Team> findAllTeams(String nameFilter, String categoryFilter, int page, int pageSize) {
        return this.teams.values().stream().filter(team -> team.getName().contains(nameFilter))
                .filter(team -> team.getCategory().contains(categoryFilter)).skip((page - 1) * pageSize).limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public Team findByIdTeam(long id) {
        return this.teams.get(id);
    }

    @Override
    public boolean createTeam(Team team) {
        if (team.getId() != -1) {
            return false;
        }
        team.setId(nextTeamId++);
        this.teams.put(team.getId(), team);
        return true;
    }

    @Override
    public boolean updateTeam(Team team) {
        if (!this.teams.containsKey(team.getId())) {
            return false;
        }
        this.teams.put(team.getId(), team);
        return true;
    }

    @Override
    public boolean deleteTeam(long id) {
        if (!this.teams.containsKey(id)) {
            return false;
        }
        this.teams.remove(id);
        return true;
    }

    @Override
    public PassFuerMannschaftssport getPass(String name) {
        return new PassFuerMannschaftssport(){
            private long value = nextId++;

            @Override
            public long getId() {
                return value;
            }
            
        };
    }

}