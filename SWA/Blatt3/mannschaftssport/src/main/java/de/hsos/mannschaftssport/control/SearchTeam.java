package de.hsos.mannschaftssport.control;

import de.hsos.mannschaftssport.control.dto.Root;

public interface SearchTeam {
    
    Root findAllTeams(String nameFilter, String categoryFilter, int page, int pageSize);
    Root findTeamById(long id, String[] include);
    Root findManagerOfTeam(long id);
    Root findPlayersOfTeam(long id);
}
