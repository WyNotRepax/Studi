package de.hsos.mannschaftssport.control;

import de.hsos.mannschaftssport.control.dto.Root;

public interface SearchPerson {
    
    Root findPersons(String nameFilter, int pageNumber, int pageSize);
    Root findPersonById(long id);

}
