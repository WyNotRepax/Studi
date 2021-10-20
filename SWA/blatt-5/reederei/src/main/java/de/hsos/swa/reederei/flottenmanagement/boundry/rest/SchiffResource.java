package de.hsos.swa.reederei.flottenmanagement.boundry.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.swa.reederei.flottenmanagement.control.SchiffService;

@Path("schiff")
@Produces(MediaType.APPLICATION_JSON)
public class SchiffResource {

    @Inject
    SchiffService schiffService;

    @GET
    public Response getAllSchiff(){
        return Response.ok(schiffService.findAllSchiff()).build();
    }

    @Path("{id}")
    @GET
    public Response getSchiff(@PathParam("id") long id) {
        return Response.ok(schiffService.findSchiffById(id)).build();
    }

    @POST
    public Response postSchiff(@QueryParam("name") String name) {
        if (name == null) {
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN)
                    .entity("Query Parameter \"name\" missing!").build();
        }
        return Response.ok(schiffService.createSchiff(name)).build();
    }
}
