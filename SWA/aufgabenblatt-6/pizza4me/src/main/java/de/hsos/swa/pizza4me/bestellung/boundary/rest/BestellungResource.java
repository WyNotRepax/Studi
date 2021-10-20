package de.hsos.swa.pizza4me.bestellung.boundary.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public class BestellungResource {

    private long kundenid;

    public BestellungResource(long id){
        this.kundenid = id;
    }

    @POST
    public Response postBestellung(){
        //TODO: Implement this!
        return null;
    }

    @GET
    public Response getBestellungen(){
        return Response.ok(String.valueOf(kundenid)).build();
    }

    @GET
    @Path("{id}")
    public Response getBestellung(){
        //TODO: Implement this!
        return null;
    }
}
