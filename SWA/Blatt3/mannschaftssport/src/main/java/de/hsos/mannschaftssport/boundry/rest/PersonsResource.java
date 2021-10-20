package de.hsos.mannschaftssport.boundry.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hsos.mannschaftssport.control.ChangeDeletePerson;
import de.hsos.mannschaftssport.control.CreatePerson;
import de.hsos.mannschaftssport.control.SearchPerson;
import de.hsos.mannschaftssport.control.dto.Root;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonsResource {
    @Inject
    SearchPerson searchPerson;

    @Inject
    CreatePerson createPerson;

    @Inject
    ChangeDeletePerson changeDeletePerson;

    @GET
    public Response getPersons(@DefaultValue("") @QueryParam("filter[name]") String nameFilter,
            @DefaultValue("1") @QueryParam("page[number]") int pageNumber,
            @DefaultValue("10") @QueryParam("page[size]") int pageSize) {
        return Response.ok(searchPerson.findPersons(nameFilter,pageNumber,pageSize)).build();
    }

    @GET
    @Path("{id}")
    public Response getPerson(@PathParam("id") long id){
        return Response.ok(searchPerson.findPersonById(id)).build();
    }

    @POST
    public Response postPerson(Root root){
        return Response.ok(createPerson.createPerson(root)).build();
        
    }

    @PUT 
    @Path("{id}")
    public Response putPerson(@PathParam("id") long id, Root root){
        return Response.ok(changeDeletePerson.changePerson(id, root)).build();
    }

    @DELETE 
    @Path("{id}")
    public Response deletePerson(@PathParam("id") long id){
        return Response.ok(changeDeletePerson.deletePerson(id)).build();
    }
}
