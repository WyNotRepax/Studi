package de.hsos.swa.pizza4me.boundary.html;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import de.hsos.swa.pizza4me.control.KundeService;
import de.hsos.swa.pizza4me.entity.Adresse;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
/**
 * @author Chrisoph Freimuth, Benno Steinkamp (pair programming)
 */
@Path("kunde")
@Produces(MediaType.TEXT_HTML)
public class KundeResource {

    public static final String KUNDEN_ID_PATH_PARAM = "kundenid";

    @Inject
    KundeService kundeService;

    @Inject
    BestellungResource bestellungResource;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance kunden();
        public static native TemplateInstance kunde();
    }

    @Path("{" + KUNDEN_ID_PATH_PARAM + "}/bestellungen")
    public BestellungResource bestellungen(@PathParam(KUNDEN_ID_PATH_PARAM) long kundenId) {
        return bestellungResource;
    }

    @GET
    public Response getKunden() {

        return Response.ok(Templates.kunden().data("kunden", this.kundeService.kundenAbfragen())).build();

    }

    @GET
    @Transactional(TxType.REQUIRES_NEW)
    @Path("{" + KUNDEN_ID_PATH_PARAM + "}")
    public Response getKunde(@PathParam(KUNDEN_ID_PATH_PARAM) long id) {
        Object kunde = this.kundeService.kundeAbfragen(id);
        if(kunde != null){
            return Response.ok(Templates.kunde().data("kunde", kunde)).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    @Transactional(TxType.REQUIRES_NEW)
    @Path("{" + KUNDEN_ID_PATH_PARAM + "}/adresse")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postAdresse(@PathParam(KUNDEN_ID_PATH_PARAM) Long kundennummer, @FormParam("strasse") String strasse, @FormParam("hausnummer") String hausnummer, @FormParam("ort") String ort, @FormParam("plz") String plz, @FormParam("create") Boolean create) {
        Adresse adr = new Adresse();
        adr.setHausnummer(hausnummer);
        adr.setOrt(ort);
        adr.setStrasse(strasse);
        adr.setPlz(plz);
        if(create) {
            this.kundeService.adresseAnlegen(kundennummer, adr);
        }else {
            this.kundeService.adresseAendern(kundennummer, adr);
        }
        return Response.seeOther(UriBuilder.fromResource(KundeResource.class).path(KundeResource.class, "getKunde").build(kundennummer)).build();
    }

}
