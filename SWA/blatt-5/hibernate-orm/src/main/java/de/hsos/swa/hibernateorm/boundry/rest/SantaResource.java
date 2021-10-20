package de.hsos.swa.hibernateorm.boundry.rest;

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

import de.hsos.swa.hibernateorm.control.SantaClausService;

@Path("santa")
@Produces(MediaType.APPLICATION_JSON)
public class SantaResource {
    @Inject
    SantaClausService santaClausService;

    @Path("{id}")
    @GET
    public Response getGift(@PathParam("id") long id){
        return Response.ok(santaClausService.getGifts(id)).build();
    }

    @GET
    public Response getGifts(@QueryParam("filter") String nameFilter){
        if(nameFilter == null){
            return Response.ok(santaClausService.getAllGifts()).build();
        }
        return Response.ok(santaClausService.getAllGifts(nameFilter)).build();
    }

    @POST
    public Response postGift(@QueryParam("description") String description){
        santaClausService.createGift(description);
        return Response.status(Status.CREATED).build();
    }

}
