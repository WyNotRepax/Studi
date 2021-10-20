package de.hsos.cocktail.boundary.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import de.hsos.cocktail.control.CocktailService;
import de.hsos.cocktail.entity.Cocktail;

@Path("/cocktails")
@Produces(MediaType.APPLICATION_JSON)
public class CocktailResource {

    @Inject
    CocktailService cocktailService;

    @GET
    @Path("{id}")
    @Retry(maxRetries = 3)
    @Timeout(value = 3000)
    public Response getById(@PathParam("id") long id) {

        Cocktail cocktail = this.cocktailService.getById(id);
        if (cocktail == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(cocktail).build();

    }

    @GET
    @Retry(maxRetries = 3)
    @Timeout(value = 3000)
    public Response getFiltered(@QueryParam("name") String name, @QueryParam("ingredient") String ingredient) {
        if(name != null) {
         return Response.ok(this.cocktailService.getByName(name)).build();
        }else if(ingredient != null){
            return Response.ok(this.cocktailService.getByIngredient(ingredient)).build();
        }else {
            return Response.status(Status.BAD_REQUEST).build();
        }

    }

}
