package de.hsos.mannschaftssport.entity;

import java.util.List;

public interface PersonCollection {
    
    public List<Person> findAllPersons(String nameFilter, int page, int pagesize);

    public Person findByIdPerson(long id);

    public boolean createPerson(Person person);

    public boolean changePerson(Person person);

    public boolean deletePerson(long id);

}
