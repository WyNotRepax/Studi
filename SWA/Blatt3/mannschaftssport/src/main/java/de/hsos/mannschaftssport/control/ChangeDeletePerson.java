package de.hsos.mannschaftssport.control;

import de.hsos.mannschaftssport.control.dto.Root;

public interface ChangeDeletePerson {
    
    Root changePerson(long id, Root root);
    Root deletePerson(long id);

}
