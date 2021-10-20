package de.hsos.swa.pizza4me.boundary.html;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.swa.pizza4me.control.PizzaService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

/**
 * @author Chrisoph Freimuth, Benno Steinkamp (pair programming)
 */
@Path("pizza")
@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
public class PizzaResource {

    @Inject
    PizzaService pizzaService;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance pizzen();
        public static native TemplateInstance pizza();
    }

    @GET
    @Transactional(TxType.REQUIRES_NEW)
    public Response getPizzas(){
        return Response.ok(Templates.pizzen().data("pizzen", this.pizzaService.getPizzas())).build();
    }

    @GET
    @Path("{id}")
    @Transactional(TxType.REQUIRES_NEW)
    public Response getPizza(@PathParam("id")long id){
        Object result = pizzaService.getPizza(id);
        if(result == null){
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(Templates.pizza().data("pizza", result)).build();
    }
}