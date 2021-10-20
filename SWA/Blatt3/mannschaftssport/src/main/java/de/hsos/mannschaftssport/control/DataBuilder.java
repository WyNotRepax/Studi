package de.hsos.mannschaftssport.control;

import java.util.List;

import javax.ws.rs.core.UriBuilder;

import de.hsos.mannschaftssport.boundry.rest.PersonsResource;
import de.hsos.mannschaftssport.boundry.rest.TeamsResource;
import de.hsos.mannschaftssport.control.dto.Data;
import de.hsos.mannschaftssport.control.dto.Links;
import de.hsos.mannschaftssport.control.dto.PersonAttributes;
import de.hsos.mannschaftssport.control.dto.PersonData;
import de.hsos.mannschaftssport.control.dto.RelationshipLinks;
import de.hsos.mannschaftssport.control.dto.Relationships;
import de.hsos.mannschaftssport.control.dto.Root;
import de.hsos.mannschaftssport.control.dto.SelfLinks;
import de.hsos.mannschaftssport.control.dto.TeamAttributes;
import de.hsos.mannschaftssport.control.dto.TeamData;
import de.hsos.mannschaftssport.entity.Person;
import de.hsos.mannschaftssport.entity.Team;

public class DataBuilder {
    private boolean _withAttributes;
    private boolean _withRelations;

    DataBuilder() {
        this._withAttributes = false;
        this._withRelations = false;
    }

    TeamDataBuilder from(Team team) {
        return new TeamDataBuilder(team).withAttributes(_withAttributes).withRelations(_withRelations);
    }

    PersonDataBuilder from(Person person) {
        return new PersonDataBuilder(person).withAttributes(_withAttributes);
    }

    public DataBuilder withAttributes() {
        this._withAttributes = true;
        return this;
    }

    public DataBuilder withAttributes(boolean value) {
        this._withAttributes = value;
        return this;
    }

    public DataBuilder withRelations() {
        this._withRelations = true;
        return this;
    }

    public DataBuilder withRelations(boolean value) {
        this._withRelations = value;
        return this;
    }

    public static class TeamDataBuilder extends DataBuilder {
        private Team team;
        private boolean _withAttributes;
        private boolean _withRelations;

        private TeamDataBuilder(Team team) {
            this.team = team;
            this._withAttributes = false;
            this._withRelations = false;
        }

        public TeamData build() {
            TeamAttributes attributes = null;
            if (_withAttributes) {
                attributes = new TeamAttributes(team.getName(), team.getCategory());
            }
            Relationships relationships = null;
            if (_withRelations) {
                Person manager = team.getManager();
                Root managerRoot = null;
                if (manager != null) {
                    Links links = new RelationshipLinks(
                            UriBuilder.fromResource(TeamsResource.class).path(String.valueOf(team.getId()))
                                    .path("relationships").path("manager").build().toString(),
                            UriBuilder.fromResource(PersonsResource.class).path(String.valueOf(manager.getId()))
                                    .build().toString());
                    managerRoot = new RootBuilder().data(new DataBuilder().from(manager).build()).links(links).build();

                }
                List<Person> players = team.getPlayers();
                Root teamRoot = null;
                if (players.size() > 0) {
                    Data[] teamData = players.stream().map(player -> new DataBuilder().from(player).build())
                            .toArray(Data[]::new);
                    Links links = new SelfLinks(UriBuilder.fromResource(TeamsResource.class).path(String.valueOf(team.getId()))
                    .path("relationships").path("players").build().toString());
                    teamRoot = new RootBuilder().data(teamData).links(links).build();
                }
                relationships = new Relationships(managerRoot, teamRoot);
            }
            return new TeamData(team.getId(), attributes, relationships);
        }

        public TeamDataBuilder withAttributes() {
            this._withAttributes = true;
            return this;
        }

        public TeamDataBuilder withAttributes(boolean value) {
            this._withAttributes = value;
            return this;
        }

        public TeamDataBuilder withRelations() {
            this._withRelations = true;
            return this;
        }

        public TeamDataBuilder withRelations(boolean value) {
            this._withRelations = value;
            return this;
        }
    }

    public static class PersonDataBuilder {
        private Person person;
        private boolean _withAttributes;

        private PersonDataBuilder(Person person) {
            this.person = person;
            this._withAttributes = false;
        }

        PersonData build() {
            PersonAttributes attributes = null;
            if (_withAttributes) {
                attributes = new PersonAttributes(person.getName());
            }
            return new PersonData(person.getId(), attributes);
        }

        public PersonDataBuilder withAttributes() {
            this._withAttributes = true;
            return this;
        }

        public PersonDataBuilder withAttributes(boolean value) {
            this._withAttributes = value;
            return this;
        }
    }
}
