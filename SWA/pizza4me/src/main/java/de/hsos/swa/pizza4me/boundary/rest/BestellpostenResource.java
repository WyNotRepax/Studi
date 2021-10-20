package de.hsos.swa.pizza4me.boundary.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.swa.pizza4me.control.BestellpostenService;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class BestellpostenResource {

    @Inject
    BestellpostenService bestellpostenService;

    @POST
    @Transactional(TxType.REQUIRED)
    public Response postBestellposten(@PathParam(KundeResource.KUNDEN_ID_PATH_PARAM) long kundenId,
            @PathParam(BestellungResource.BESTELLUNG_ID_PATH_PARAM) long bestellungId,
            @QueryParam("pizza") Long pizzaId, @QueryParam("menge") Integer menge) {
        if (pizzaId == null || menge == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        return Response.ok(bestellpostenService.bestellpostenAnlegen(kundenId, bestellungId, pizzaId, menge)).build();
    }

    @PUT
    @Transactional(TxType.REQUIRED)
    @Path("{bestellpostenId}")
    public Response putBestellposten(@PathParam(KundeResource.KUNDEN_ID_PATH_PARAM) long kundenId,
            @PathParam(BestellungResource.BESTELLUNG_ID_PATH_PARAM) long bestellungId,
            @PathParam("bestellpostenId") long bestellpostenId, @QueryParam("menge") Integer menge) {
        if (menge == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        return Response.ok(bestellpostenService.bestellpostenAendern(kundenId, bestellungId, bestellpostenId, menge))
                .build();
    }

    @DELETE
    @Transactional(TxType.REQUIRED)
    @Path("{bestellpostenId}")
    public Response deleteBestellposten(@PathParam(KundeResource.KUNDEN_ID_PATH_PARAM) long kundenId,
            @PathParam(BestellungResource.BESTELLUNG_ID_PATH_PARAM) long bestellungId,
            @PathParam("bestellpostenId") long bestellpostenId) {
        if (bestellpostenService.bestellpostenLoeschen(kundenId, bestellungId, bestellpostenId)) {
            return Response.ok().build();
        }
        return Response.status(Status.BAD_REQUEST).build();

    }
}
