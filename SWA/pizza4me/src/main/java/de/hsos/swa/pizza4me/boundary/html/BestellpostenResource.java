package de.hsos.swa.pizza4me.boundary.html;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import de.hsos.swa.pizza4me.control.BestellpostenService;
/**
 * @author Chrisoph Freimuth, Benno Steinkamp (pair programming)
 */
@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
public class BestellpostenResource {

    @Inject
    BestellpostenService bestellpostenService;

    @POST
    @Transactional(TxType.REQUIRED)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postBestellposten(@PathParam(KundeResource.KUNDEN_ID_PATH_PARAM) long kundenId,
            @PathParam(BestellungResource.BESTELLUNG_ID_PATH_PARAM) long bestellungId,
            @FormParam("pizzaid") Long pizzaId, @FormParam("menge") Integer menge) {
        if (pizzaId == null || menge == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        bestellpostenService.bestellpostenAnlegen(kundenId, bestellungId, pizzaId, menge);
        return Response.seeOther(UriBuilder.fromResource(KundeResource.class).path(KundeResource.class, "bestellungen").path(BestellungResource.class, "getBestellung").build(kundenId, bestellungId)).build();
    }

    @POST
    @Transactional(TxType.REQUIRED)
    @Path("{bestellpostenId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response putBestellposten(@PathParam(KundeResource.KUNDEN_ID_PATH_PARAM) long kundenId,
            @PathParam(BestellungResource.BESTELLUNG_ID_PATH_PARAM) long bestellungId,
            @PathParam("bestellpostenId") long bestellpostenId, @FormParam("menge") Integer menge) {
        if (menge == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        bestellpostenService.bestellpostenAendern(kundenId, bestellungId, bestellpostenId, menge);
        return Response.seeOther(UriBuilder.fromResource(KundeResource.class).path(KundeResource.class, "bestellungen").path(BestellungResource.class, "getBestellung").build(kundenId, bestellungId)).build();
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
