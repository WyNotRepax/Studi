package de.hsos.mannschaftssport.control;

import de.hsos.mannschaftssport.control.dto.Root;

public interface ChangeDeleteTeam {

    Root changeTeam(long id, Root root);

    Root deleteTeam(long id);

    Root setManagerOfTeam(long id, Root root);

    Root deletePlayerOfTeam(long id);

    Root addPlayersOfTeam(long id, Root root);

    Root setPlayersOfTeam(long id, Root root);

    Root deleteManagerOfTeam(long id);
}
