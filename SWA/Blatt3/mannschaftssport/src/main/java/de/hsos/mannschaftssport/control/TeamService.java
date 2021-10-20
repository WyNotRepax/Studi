package de.hsos.mannschaftssport.control;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriBuilder;

import de.hsos.mannschaftssport.boundry.rest.TeamsResource;
import de.hsos.mannschaftssport.control.dto.Data;
import de.hsos.mannschaftssport.control.dto.Error;
import de.hsos.mannschaftssport.control.dto.Links;
import de.hsos.mannschaftssport.control.dto.PersonData;
import de.hsos.mannschaftssport.control.dto.Root;
import de.hsos.mannschaftssport.control.dto.SelfLinks;
import de.hsos.mannschaftssport.control.dto.TeamAttributes;
import de.hsos.mannschaftssport.control.dto.TeamData;
import de.hsos.mannschaftssport.entity.Person;
import de.hsos.mannschaftssport.entity.PersonCollection;
import de.hsos.mannschaftssport.entity.Team;
import de.hsos.mannschaftssport.entity.TeamCollection;

@ApplicationScoped
public class TeamService implements SearchTeam, CreateTeam, ChangeDeleteTeam {

    @Inject
    TeamCollection teams;

    @Inject
    PersonCollection persons;

    @Override
    public Root changeTeam(long id, Root root) {
        if (!(root.getData() instanceof TeamData)) {
            return new RootBuilder()
                    .error(Error.badData().detail("data attribute does not contain Team").source("data").build())
                    .build();
        }
        TeamData teamData = (TeamData) root.getData();
        if (teamData.getAttributes() == null) {
            return new RootBuilder()
                    .error(Error.badData().detail("attributes attribute is missing").source("data.attributes").build())
                    .build();
        }
        if (!(teamData.getAttributes() instanceof TeamAttributes)) {
            return new RootBuilder().error(
                    Error.badData().detail("attributes attribute is malformed").source("data.attributes").build())
                    .build();
        }
        Team team = teams.findByIdTeam(id);
        String name = ((TeamAttributes) teamData.getAttributes()).getName();
        if (name != null) {
            team.setName(name);
        }
        String category = ((TeamAttributes) teamData.getAttributes()).getCategory();
        if (category != null) {
            team.setCategory(category);
        }
        teams.updateTeam(team);
        return findTeamById(id, new String[0]);
    }

    @Override
    public Root deleteTeam(long id) {
        if (teams.deleteTeam(id)) {
            return new RootBuilder().build();
        }
        return new RootBuilder().error(Error.notFound().detail("Team with id does not exist").source("id").build())
                .build();
    }

    @Override
    public Root setManagerOfTeam(long id, Root root) {
        if (!(root.getData() instanceof PersonData)) {
            return new RootBuilder()
                    .error(Error.badData().detail("data attribute does not contain Person").source("data").build())
                    .build();
        }
        PersonData personData = (PersonData) root.getData();
        if (personData.getId() == null) {
            return new RootBuilder().error(Error.badData().detail("id attribute is missing").source("data.id").build())
                    .build();
        }
        Team team = teams.findByIdTeam(id);
        Person person = persons.findByIdPerson(personData.getId());
        team.setManager(person);
        return findTeamById(id, new String[] { "manager" });
    }

    @Override
    public Root deletePlayerOfTeam(long id) {
        Team team = teams.findByIdTeam(id);
        team.getPlayers().removeIf(persons->persons.getId() == id);
        return findTeamById(id, new String[0]);
    }

    @Override
    public Root addPlayersOfTeam(long id, Root root) {
        if (!(root.getData() instanceof Data[])) {
            return new RootBuilder()
                    .error(Error.badData().detail("data attribute does not contain Person Array").source("data").build())
                    .build();
        }
        Data[] allData = (Data[]) root.getData();
        Team team = teams.findByIdTeam(id);
        for(Data data: allData){
            if(!(data instanceof PersonData)){
                return new RootBuilder()
                    .error(Error.badData().detail("data attribute does not contain Person").source("data").build())
                    .build();
            }
            PersonData personData = (PersonData) data;
            if (personData.getId() == null) {
                return new RootBuilder().error(Error.badData().detail("id attribute is missing").source("data.id").build())
                        .build();
            }
            Person person = persons.findByIdPerson(personData.getId());
            team.getPlayers().add(person);
        }
        return findTeamById(id,new String[0]);
    }

    @Override
    public Root setPlayersOfTeam(long id, Root root) {
        Team team = teams.findByIdTeam(id);
        team.getPlayers().clear();
        return addPlayersOfTeam(id,root);
    }

    @Override
    public Root createTeam(Root root) {
        if (!(root.getData() instanceof TeamData)) {
            return new RootBuilder()
                    .error(Error.badData().detail("data attribute does not contain Person").source("data").build())
                    .build();
        }
        TeamData teamData = (TeamData) root.getData();
        if (teamData.getAttributes() == null) {
            return new RootBuilder()
                    .error(Error.badData().detail("attributes attribute is missing").source("data.attributes").build())
                    .build();
        }
        if (!(teamData.getAttributes() instanceof TeamAttributes)) {
            return new RootBuilder().error(
                    Error.badData().detail("attributes attribute is malformed").source("data.attributes").build())
                    .build();
        }
        String name = ((TeamAttributes) teamData.getAttributes()).getName();
        String category = ((TeamAttributes) teamData.getAttributes()).getName();
        if (name == null) {
            return new RootBuilder().error(
                    Error.badData().detail("name attribute is missing").source("data.attributes.name").build())
                    .build();
        }
        Team team = new Team(-1, null, null, name, category);
        teams.createTeam(team);
        return findTeamById(team.getId(),new String[0]);
    }

    @Override
    public Root findAllTeams(String nameFilter, String categoryFilter, int page, int pageSize) {
        List<Team> foundTeams = teams.findAllTeams(nameFilter, categoryFilter, page, pageSize);
        return new RootBuilder()
                .data(foundTeams.stream().map(team -> new DataBuilder().from(team).build()).toArray(Data[]::new))
                .build();
    }

    @Override
    public Root findTeamById(long id, String[] include) {
        Team team = teams.findByIdTeam(id);
        boolean includePlayers = false;
        boolean includeManager = false;
        for(String s: include){
            if(s.equals("manager")){
                includeManager = true;
            }else if(s.equals("players")){
                includePlayers = true;
            }
        }
        Links links = new SelfLinks(UriBuilder.fromResource(TeamsResource.class).path(String.valueOf(id)).build().toString());
        RootBuilder rootBuilder = new RootBuilder().data(new DataBuilder().from(team).withAttributes().withRelations().build()).links(links);
        
        if(includeManager && team.getManager() != null){
            rootBuilder.included(new DataBuilder().from(team.getManager()).withAttributes().build());
        }
        if(includePlayers && team.getPlayers().size() > 0){
            for(Person player: team.getPlayers()){
                rootBuilder.included(new DataBuilder().from(player).withAttributes().build());
            }
        }
        return rootBuilder.build();
    }

    @Override
    public Root findManagerOfTeam(long id) {
        Team team = teams.findByIdTeam(id);
        if(team == null){
            return new RootBuilder().error(Error.notFound().detail("Team with id does not exist").source("id").build()).build();
        }
        if(team.getManager() == null){
            return new RootBuilder().build();
        }
        return new RootBuilder().data(new DataBuilder().from(team.getManager()).withAttributes().build()).build();
    }

    @Override
    public Root findPlayersOfTeam(long id) {
        Team team = teams.findByIdTeam(id);
        if(team == null){
            return new RootBuilder().error(Error.notFound().detail("Team with id does not exist").source("id").build()).build();
        }
        return new RootBuilder()
                .data(team.getPlayers().stream().map(person -> new DataBuilder().from(person).withAttributes().build()).toArray(Data[]::new))
                .build();
    }

    @Override
    public Root deleteManagerOfTeam(long id) {
        Team team = teams.findByIdTeam(id);
        if(team == null){
            return new RootBuilder().error(Error.notFound().detail("Team with id does not exist").source("id").build()).build();
        }
        team.setManager(null);
        return new RootBuilder().build();
    }

}
