package de.hsos.swa.reederei.flottenmanagement.control;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.UriBuilder;

import de.hsos.swa.reederei.auftragsmanagement.control.events.NeuerAuftrag;
import de.hsos.swa.reederei.flottenmanagement.boundry.rest.SchiffResource;
import de.hsos.swa.reederei.flottenmanagement.control.events.AuftragAngenommen;
import de.hsos.swa.reederei.flottenmanagement.entity.Schiff;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class EventService {

    @Inject
    Event<AuftragAngenommen> auftragAngenommenEvent;

    @Transactional
    void onNewAuftrag(@Observes NeuerAuftrag newAuftrag) {
        Schiff schiff = Schiff.find("from Schiff schiff where schiff.frei = :isFrei", Parameters.with("isFrei", true))
                .firstResult();
        if (schiff == null) {
            return;
        }
        String url = UriBuilder.fromResource(SchiffResource.class).path(SchiffResource.class,"getSchiff").build(schiff.id).toString();

        schiff.setFrei(false);
        Schiff.persist(schiff);
        auftragAngenommenEvent.fire(new AuftragAngenommen(url, newAuftrag.auftragId));
    }
}
