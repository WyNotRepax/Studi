package de.hsos.cocktail.gateway;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v1/1")
@RegisterRestClient(configKey = "cocktailAPI")
public interface CocktailGateway {
    
    @GET
    @Path("/search.php")
    JsonObject getByName(@QueryParam("s") String name);

    @GET
    @Path("/lookup.php")
    JsonObject getById(@QueryParam("i") long id);

    @GET
    @Path("/filter.php")
    JsonObject getByIngredient(@QueryParam("i") String ingredient);

}
