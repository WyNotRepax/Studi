package de.hsos.mannschaftssport.entity;

import java.util.List;

/**
 * TeamCollection
 */
public interface TeamCollection {

    public List<Team> findAllTeams(String nameFilter, String categoryFilter, int page, int pageSize);

    public Team findByIdTeam(long id);

    public boolean createTeam(Team team);

    public boolean updateTeam(Team team);

    public boolean deleteTeam(long id);

}