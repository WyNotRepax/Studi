package de.hsos.swa.pizza4me.bestellung.boundary.rest;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Path("kunde")
public class KundeResource {
    
    @Path("{id}/bestellungen")
    public BestellungResource getBestellungen(@PathParam("id") long id){
        return new BestellungResource(id);
    }
}
