package de.hsos.swa.pizza4me.boundary.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.swa.pizza4me.control.BestellungService;

/**
 * @author Chrisoph Freimuth, Benno Steinkamp (pair programming)
 */
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class BestellungResource {

    public static final String BESTELLUNG_ID_PATH_PARAM = "bestellungid";

    @Inject
    private BestellungService bestellungService;

    @Inject
    private BestellpostenResource bestellpostenResource;

    @POST
    @Transactional(TxType.REQUIRES_NEW)
    public Response postBestellung(@PathParam(KundeResource.KUNDEN_ID_PATH_PARAM) long kundenid) {
        return Response.ok(this.bestellungService.bestellungAnlegen(kundenid)).build();
    }

    @GET
    @Transactional(TxType.REQUIRES_NEW)
    public Response getBestellungen(@PathParam(KundeResource.KUNDEN_ID_PATH_PARAM) long kundenid) {
        return Response.ok(this.bestellungService.bestellungenAbfragen(kundenid)).build();
    }

    @GET
    @Transactional(TxType.REQUIRES_NEW)
    @Path("{"+BESTELLUNG_ID_PATH_PARAM+"}")
    public Response getBestellung(@PathParam(KundeResource.KUNDEN_ID_PATH_PARAM) long kundenid, @PathParam(BESTELLUNG_ID_PATH_PARAM) long bestellungId) {
        Object bestellung =  this.bestellungService.bestellungAbfragen(kundenid,bestellungId);
        if(bestellung == null){
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(bestellung).build();
    }

    @Path("{"+BESTELLUNG_ID_PATH_PARAM+"}/bestellposten")
    public BestellpostenResource bestellposten(@PathParam(BESTELLUNG_ID_PATH_PARAM) long bestellungId){
        return this.bestellpostenResource;
    }
}
