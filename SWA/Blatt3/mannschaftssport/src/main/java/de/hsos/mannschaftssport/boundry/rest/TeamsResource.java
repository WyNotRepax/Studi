package de.hsos.mannschaftssport.boundry.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hsos.mannschaftssport.control.ChangeDeleteTeam;
import de.hsos.mannschaftssport.control.CreateTeam;
import de.hsos.mannschaftssport.control.SearchTeam;
import de.hsos.mannschaftssport.control.dto.Root;

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
public class TeamsResource {

    @Inject
    SearchTeam searchTeam;

    @Inject
    ChangeDeleteTeam changeDeleteTeam;

    @Inject
    CreateTeam createTeam;

    @GET
    public Response getTeams(@DefaultValue("")@QueryParam("filter[name]") String nameFilter,
            @DefaultValue("")@QueryParam("filter[category]") String categoryFilter,
            @DefaultValue("1") @QueryParam("page[number]") int pageNumber,
            @DefaultValue("10") @QueryParam("page[size]") int pageSize) {
        return Response.ok(searchTeam.findAllTeams(nameFilter, categoryFilter, pageNumber, pageSize)).build();
    }

    @Path("{id}")
    @GET
    public Response getTeam(@PathParam("id") long id, @QueryParam("include") String[] include) {
        return Response.ok(searchTeam.findTeamById(id, include)).build();
    }

    @POST
    public Response postTeam(Root root) {
        return Response.ok(createTeam.createTeam(root)).build();
    }

    @Path("{id}")
    @PUT
    public Response putTeam(@PathParam("id") long id, Root root) {
        return Response.ok(changeDeleteTeam.changeTeam(id, root)).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeam(@PathParam("id") long id) {
        return Response.ok(changeDeleteTeam.deleteTeam(id)).build();
    }

    /**
     * Manager relationship
     */

    @Path("{id}/relationships/manager")
    @GET
    public Response getManagerOfTeam(@PathParam("id") long id) {
        return Response.ok(searchTeam.findManagerOfTeam(id)).build();
    }

    @Path("{id}/relationships/manager")
    @DELETE
    public Response deleteManagerOfTeam(@PathParam("id") long id) {
        return Response.ok(changeDeleteTeam.deleteManagerOfTeam(id)).build();
    }

    @Path("{id}/relationships/manager")
    @PUT
    public Response putManagerOfTeam(@PathParam("id") long id, Root root) {
        return Response.ok(changeDeleteTeam.setManagerOfTeam(id,root)).build();
    }

    @Path("{id}/relationships/manager")
    @POST
    public Response postManagerOfTeam(@PathParam("id") long id, Root root) {
        return Response.ok(changeDeleteTeam.setManagerOfTeam(id,root)).build();
    }

    /**
     * Player relationship
     */

    @Path("{id}/relationships/players")
    @GET
    public Response getPlayersOfTeam(@PathParam("id") long id) {
        return Response.ok(searchTeam.findPlayersOfTeam(id)).build();
    }

    @Path("{id}/relationships/players/{playerId}")
    @DELETE
    public Response deletePlayerOfTeam(@PathParam("id") long id, @PathParam("playerId") long playerId) {
        return Response.ok(changeDeleteTeam.deletePlayerOfTeam(id)).build();
    }

    @Path("{id}/relationships/players")
    @PUT
    public Response putPlayersOfTeam(@PathParam("id") long id, Root root) {
        return Response.ok(changeDeleteTeam.addPlayersOfTeam(id,root)).build();
    }

    @Path("{id}/relationships/players")
    @POST
    public Response postPlayersOfTeam(@PathParam("id") long id, Root root) {
        return Response.ok(changeDeleteTeam.setPlayersOfTeam(id,root)).build();
    }
}