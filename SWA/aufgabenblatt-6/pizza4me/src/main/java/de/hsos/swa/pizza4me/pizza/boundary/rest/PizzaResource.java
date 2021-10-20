package de.hsos.swa.pizza4me.pizza.boundary.rest;

import javax.enterprise.context.RequestScoped;
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

import de.hsos.swa.pizza4me.pizza.control.PizzaService;

@Path("pizza")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class PizzaResource {

    @Inject
    PizzaService pizzaService;

    @GET
    public Response getPizzas(){
        return Response.ok(pizzaService.getPizzas()).build();
    }

    @GET
    @Path("{id}")
    public Response getPizza(@PathParam("id")long id){
        Object result = pizzaService.getPizza(id);
        if(result == null){
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(result).build();
    }

    @POST
    public Response postPizza(@QueryParam("name") String name, @QueryParam("beschreibung") String beschreibung, @QueryParam("preis") String preis){
        if(name == null || beschreibung == null || preis == null){
            return Response.status(Status.BAD_REQUEST).build();
        }
        return Response.status(Status.CREATED).entity(pizzaService.createPizza(name, beschreibung, preis)).build();
    }
}