package de.hsos.mocktail.boundary.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import de.hsos.mocktail.entity.Mocktail;
import de.hsos.mocktail.management.control.ChangeDelete;
import de.hsos.mocktail.management.control.Create;
import de.hsos.mocktail.search.control.SearchId;
import de.hsos.mocktail.search.control.SearchList;

@Path("/mocktails")
@Produces(MediaType.APPLICATION_JSON)
public class MocktailResource {

    @Inject
    Logger log;

    @Inject
    SearchList searchList;

    @Inject
    SearchId searchId;

    @Inject
    ChangeDelete changeDelete;

    @Inject
    Create create;

    @GET
    @Operation(
        summary = "Get Mocktails",
        description = "Get list of all mocktails"
    )
    @APIResponse(
        responseCode = "200",
        description = "Mocktail list",
        content = @Content(mediaType = "application/json")
    )
    @Schema(implementation = Mocktail.class)
    public Response getListe() {
        log.debug("GET-Request getList started");
        log.info(log.isTraceEnabled());
        Mocktail[] mocktails = this.searchList.getMocktails();
        log.tracef("returning %d mocktails", mocktails.length);
        log.debug("GET-Request getList ended");
        return Response.ok(mocktails).build();
    }

    @GET
    @Path("{id}")
    @Operation(
        summary = "Get Mocktail",
        description = "Get mocktail by id"
    )
    @APIResponse(
        responseCode = "200",
        description = "Get one Mocktail",
        content = @Content(mediaType = "application/json")
    )
    @Schema(implementation = Mocktail.class)
    public Response getMocktail(@PathParam("id") long id) {
        log.debug("GET-Request getMocktail started");
        Mocktail mocktail = this.searchId.getMocktail(id);
        log.tracef("returning %s", String.valueOf(mocktail));
        log.debug("GET-Request getList ended");
        return Response.ok(mocktail).build();
    }

    @POST
    @Operation(
        summary = "Create Mocktail",
        description = "Create new Mocktail with params"
    )
    @APIResponse(
        responseCode = "200",
        description = "Created Mocktail",
        content = @Content(mediaType = "application/json")
    )
    @Schema(implementation = Mocktail.class)
    @APIResponse(
        responseCode = "404",
        description = "Mocktail not found"
    )
    @Counted(
        name = "newMocktailTries",
        description = "How many times mocktail creation has been attemted."
    )
    public Response newMocktail(@QueryParam("name") String name, @QueryParam("rezept") String rezept,
            @QueryParam("zutaten") String zutaten, @QueryParam("autor") String autor) {
        log.debug("Post-Request newMocktail started");
        Mocktail mocktail = this.create.createMocktail(name, rezept, zutaten, autor);
        if (mocktail == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        log.tracef("returning %s", String.valueOf(mocktail));
        log.debug("Post-Request newMocktail ended");
        return Response.status(Status.CREATED).entity(mocktail).build();
    }

    @PUT
    @Path("{id}")
    @Operation(
        summary = "Modify Mocktail",
        description = "Modify existing mocktail with params"
    )
    @APIResponse(
        responseCode = "200",
        description = "Modified Mocktail",
        content = @Content(mediaType = "application/json")
    )
    @Schema(implementation = Mocktail.class)
    @APIResponse(
        responseCode = "404",
        description = "Mocktail not found"
    )
    @Timed(
        name = "changeTimer",
        description = "A measure of how long it takes to perform an update."
    )
    public Response changeMocktail(@PathParam("id") long id, @QueryParam("name") String name,
            @QueryParam("rezept") String rezept, @QueryParam("zutaten") String zutaten,
            @QueryParam("autor") String autor) {
        log.debug("Put-Request changeMocktail started");
        Mocktail mocktail = this.changeDelete.changeMocktail(id, name, rezept, zutaten, autor);
        if (mocktail == null) {
            log.errorf("PUT id:{%d} name:{%s} rezept:{%s}, zutaten:{%s} autor:{%s}", id, name, rezept, zutaten, autor);
            log.debug("Delete-Request deleteMocktail ended");
            return Response.status(Status.BAD_REQUEST).build();
        }
        log.debug("Put-Request changeMocktail ended");
        return Response.ok().entity(mocktail).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(
        summary = "Delete Mocktail",
        description = "Delete mocktail with id"
    )
    @APIResponse(
        responseCode = "404",
        description = "Mocktail not Found"
    )
    public Response deleteMocktail(@PathParam("id") long id) {
        log.debug("Delete-Request deleteMocktail started");
        if (this.changeDelete.deleteMocktail(id)) {
            log.debug("Delete-Request deleteMocktail ended");
            return Response.ok().build();
        }
        log.debug("Delete-Request deleteMocktail ended");
        return Response.status(Status.BAD_REQUEST).build();

    }
}