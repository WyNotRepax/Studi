package de.hsos.swa.ab06.boundary.rs;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
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

import de.hsos.swa.ab06.control.KundenService;
import de.hsos.swa.ab06.entity.Adresse;

@Path("kunden")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class KundenResource {
    @Inject
    KundenService kundenService;

    @POST
    public Response postKunde(@QueryParam("vorname") String vorname, @QueryParam("nachname") String nachname) {
        if (vorname == null || nachname == null) {
            return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity("Missing Query Parameters")
                    .build();
        }
        kundenService.kundeAnlegen(vorname, nachname);
        return Response.ok().build();
    }

    @GET
    public Response getKunden() {
        return Response.ok(kundenService.kundenAbfragen()).build();
    }

    @GET
    @Path("{id}")
    public Response getKunde(@PathParam("id") Long kundennummer) {
        return Response.ok(kundenService.kundeAbfragen(kundennummer)).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteKunde(@PathParam("id") Long kundennummer) {
        if (kundenService.kundeLoeschen(kundennummer)) {
            return Response.ok().build();
        } else {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("{id}/adresse")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postAdresse(@PathParam("id") Long kundennummer, Adresse adr) {
        kundenService.adresseAnlegen(kundennummer, adr);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}/adresse")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAdresse(@PathParam("id") Long kundennummer, Adresse adr) {
        kundenService.adresseAendern(kundennummer, adr);
        return Response.ok().build();
    }

    @GET
    @Path("{id}/adresse")
    public Response getAdresse(@PathParam("id") Long kundennummer) {
        return Response.ok(kundenService.adresseAbfragen(kundennummer)).build();
    }

    @DELETE
    @Path("{id}/adresse")
    public Response putAdresse(@PathParam("id") Long kundennummer) {
        if(kundenService.adresseLoeschen(kundennummer)){
            return Response.ok().build();
        }else{
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    public Response deleteKunden(){
        this.kundenService.alleKundenLöschen();
        return Response.ok().build();
    }
}