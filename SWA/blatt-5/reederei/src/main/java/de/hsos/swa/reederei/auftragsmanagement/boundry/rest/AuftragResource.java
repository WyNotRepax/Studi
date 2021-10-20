package de.hsos.swa.reederei.auftragsmanagement.boundry.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.swa.reederei.auftragsmanagement.control.AuftragService;
import de.hsos.swa.reederei.auftragsmanagement.entity.Auftrag;

@Path("auftrag")
@Produces(MediaType.APPLICATION_JSON)
public class AuftragResource {
    @Inject
    AuftragService auftragService;

    @GET
    public Response getAllAuftrag(){
        return Response.ok(auftragService.findAllAuftrag()).build();
    }

    @Path("{id}")
    @GET
    public Response getAuftrag(@PathParam("id") Long id) {
        return Response.ok(auftragService.findAuftragById(id)).build();
    }

    @POST
    public Response postAuftrag(@QueryParam("beschreibung") String beschreibung) {
        if (beschreibung == null) {
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN)
                    .entity("Query Parameter \"beschreibung\" missing!").build();
        }
        return Response.ok(auftragService.createAuftrag(beschreibung)).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAuftrag(@PathParam("id") long id, Auftrag auftrag) {
        if (auftrag.id != null && auftrag.id != id) {
            return Response.status(Status.BAD_REQUEST).entity("Property \"Auftrag.id\" cannot be changed!")
                    .type(MediaType.TEXT_PLAIN).build();
        }
        auftrag.id = id;
        Auftrag changedAuftrag = auftragService.changeAuftrag(auftrag);
        if (changedAuftrag == null) {
            return Response.status(Status.NOT_FOUND).entity(String.format("Auftrag with id %d does not exist!", id))
                    .type(MediaType.TEXT_PLAIN).build();
        }
        return Response.ok(changedAuftrag).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteAuftrag(@PathParam("id") long id) {
        if (auftragService.deleteAuftrag(id)) {
            return Response.ok().build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

}