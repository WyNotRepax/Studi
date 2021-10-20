package de.hsos.swa.reederei.auftragsmanagement.control;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

import de.hsos.swa.reederei.auftragsmanagement.entity.Auftrag;
import de.hsos.swa.reederei.flottenmanagement.control.events.AuftragAngenommen;

@ApplicationScoped
public class EventService {
    @Transactional
    void onAuftragAngenommen(@Observes AuftragAngenommen auftragAngenommen){
        Auftrag auftrag = Auftrag.findById(auftragAngenommen.auftragId);
        auftrag.setShip(auftragAngenommen.schiffUrl);
        auftrag.persist();
    }
}
