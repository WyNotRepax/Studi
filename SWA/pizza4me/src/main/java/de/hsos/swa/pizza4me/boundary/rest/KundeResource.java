package de.hsos.swa.pizza4me.boundary.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.swa.pizza4me.control.KundeService;
import de.hsos.swa.pizza4me.entity.Adresse;

/**
 * @author Chrisoph Freimuth, Benno Steinkamp (pair programming)
 */
@Path("api/kunde")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class KundeResource {

    public static final String KUNDEN_ID_PATH_PARAM = "kundenid";

    @Inject
    KundeService kundeService;

    @Inject
    BestellungResource bestellungResource;

    @Path("{" + KUNDEN_ID_PATH_PARAM + "}/bestellungen")
    public BestellungResource bestellungen(@PathParam(KUNDEN_ID_PATH_PARAM) long kundenId) {
        return bestellungResource;
    }

    @POST
    @Transactional(TxType.REQUIRES_NEW)
    public Response postKunde() {
        Object object = kundeService.kundenAnlegen();
        if (object == null) {
            return Response.status(Status.BAD_GATEWAY).build();
        }
        return Response.status(Status.CREATED).entity(object).build();
    }

    @GET
    @Transactional(TxType.REQUIRES_NEW)
    public Response getKunden() {
        return Response.ok(kundeService.kundenAbfragen()).build();
    }

    @GET
    @Transactional(TxType.REQUIRES_NEW)
    @Path("{" + KUNDEN_ID_PATH_PARAM + "}")
    public Response getKunde(@PathParam(KUNDEN_ID_PATH_PARAM) long id) {
        return Response.ok(kundeService.kundeAbfragen(id)).build();
    }

    @POST
    @Transactional(TxType.REQUIRES_NEW)
    @Path("{" + KUNDEN_ID_PATH_PARAM + "}/adresse")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postAdresse(@PathParam(KUNDEN_ID_PATH_PARAM) Long kundennummer, Adresse adr) {
        kundeService.adresseAnlegen(kundennummer, adr);
        return Response.ok().build();
    }

    @PUT
    @Transactional(TxType.REQUIRES_NEW)
    @Path("{" + KUNDEN_ID_PATH_PARAM + "}/adresse")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAdresse(@PathParam(KUNDEN_ID_PATH_PARAM) Long kundennummer, Adresse adr) {
        kundeService.adresseAendern(kundennummer, adr);
        return Response.ok().build();
    }

    @GET
    @Transactional(TxType.REQUIRES_NEW)
    @Path("{" + KUNDEN_ID_PATH_PARAM + "}/adresse")
    public Response getAdresse(@PathParam(KUNDEN_ID_PATH_PARAM) Long kundennummer) {
        return Response.ok(kundeService.adresseAbfragen(kundennummer)).build();
    }

    @DELETE
    @Transactional(TxType.REQUIRES_NEW)
    @Path("{" + KUNDEN_ID_PATH_PARAM + "}/adresse")
    public Response putAdresse(@PathParam(KUNDEN_ID_PATH_PARAM) Long kundennummer) {
        if (kundeService.adresseLoeschen(kundennummer)) {
            return Response.ok().build();
        } else {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }
}
