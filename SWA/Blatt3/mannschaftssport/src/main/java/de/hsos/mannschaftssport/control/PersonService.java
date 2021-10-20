package de.hsos.mannschaftssport.control;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriBuilder;

import de.hsos.mannschaftssport.acl.PassManagement;
import de.hsos.mannschaftssport.boundry.rest.PersonsResource;
import de.hsos.mannschaftssport.control.dto.Data;
import de.hsos.mannschaftssport.control.dto.Error;
import de.hsos.mannschaftssport.control.dto.Links;
import de.hsos.mannschaftssport.control.dto.PersonAttributes;
import de.hsos.mannschaftssport.control.dto.PersonData;
import de.hsos.mannschaftssport.control.dto.Root;
import de.hsos.mannschaftssport.control.dto.SelfLinks;
import de.hsos.mannschaftssport.entity.Person;
import de.hsos.mannschaftssport.entity.PersonCollection;

@ApplicationScoped
public class PersonService implements SearchPerson, CreatePerson, ChangeDeletePerson {

    @Inject
    PersonCollection persons;

    @Inject
    PassManagement passManagement;

    public PersonService() {
    }

    @Override
    public Root deletePerson(long id) {
        if(persons.deletePerson(id)){
            return new RootBuilder().build();
        }
        return new RootBuilder().error(Error.notFound().detail("Person with id does not exist").source("id").build()).build();
    }

    @Override
    public Root createPerson(Root root) {
        if (!(root.getData() instanceof PersonData)) {
            return new RootBuilder()
                    .error(Error.badData().detail("data attribute does not contain Person").source("data").build())
                    .build();
        }
        PersonData personData = (PersonData) root.getData();
        if (personData.getAttributes() == null) {
            return new RootBuilder()
                    .error(Error.badData().detail("attributes attribute is missing").source("data.attributes").build())
                    .build();
        }
        if (!(personData.getAttributes() instanceof PersonAttributes)) {
            return new RootBuilder().error(
                    Error.badData().detail("attributes attribute is malformed").source("data.attributes").build())
                    .build();
        }
        String name = ((PersonAttributes) personData.getAttributes()).getName();
        if (name == null) {
            return new RootBuilder().error(
                    Error.badData().detail("name attribute is missing").source("data.attributes.name").build())
                    .build();
        }
        long id = passManagement.getPass(name).getId();
        Person person = new Person(id,name);
        persons.createPerson(person);
        return findPersonById(id);
    }

    @Override
    public Root findPersonById(long id) {
        Person person = persons.findByIdPerson(id);
        if(person == null){
            return new RootBuilder().error(Error.notFound().detail("Person with id does not exist").source("data.id").build()).build();
        }
        Links links = new SelfLinks(UriBuilder.fromResource(PersonsResource.class).path(String.valueOf(id)).build().toString());
        return new RootBuilder().data(new DataBuilder().from(person).withAttributes().build()).links(links).build();
    }

    @Override
    public Root findPersons(String nameFilter, int pageNumber, int pageSize) {
        List<Person> foundPersons = persons.findAllPersons(nameFilter, pageNumber, pageSize);
        return new RootBuilder()
                .data(foundPersons.stream().map(person -> new DataBuilder().from(person).build()).toArray(Data[]::new))
                .build();
    }

    @Override
    public Root changePerson(long id, Root root) {
        if (!(root.getData() instanceof PersonData)) {
            return new RootBuilder()
                    .error(Error.badData().detail("data attribute does not contain Person").source("data").build())
                    .build();
        }
        PersonData personData = (PersonData) root.getData();
        if (personData.getAttributes() == null) {
            return new RootBuilder()
                    .error(Error.badData().detail("attributes attribute is missing").source("data.attributes").build())
                    .build();
        }
        if (!(personData.getAttributes() instanceof PersonAttributes)) {
            return new RootBuilder().error(
                    Error.badData().detail("attributes attribute is malformed").source("data.attributes").build())
                    .build();
        }
        Person person = persons.findByIdPerson(id);
        String name = ((PersonAttributes) personData.getAttributes()).getName();
        if (name != null) {
            person.setName(name);
        }
        persons.changePerson(person);
        return findPersonById(id);
    }

}
