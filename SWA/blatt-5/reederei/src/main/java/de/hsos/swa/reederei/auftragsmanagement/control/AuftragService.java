package de.hsos.swa.reederei.auftragsmanagement.control;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;

import de.hsos.swa.reederei.auftragsmanagement.control.events.AuftragStorniert;
import de.hsos.swa.reederei.auftragsmanagement.control.events.NeuerAuftrag;
import de.hsos.swa.reederei.auftragsmanagement.entity.Auftrag;

@ApplicationScoped
public class AuftragService {
    @Inject
    Event<NeuerAuftrag> neuerAuftragEvent;

    @Inject
    Event<AuftragStorniert> auftragStroniertEvent;

    public List<Auftrag> findAllAuftrag() {
        return Auftrag.listAll();
    }

    public Auftrag findAuftragById(long id) {
        return Auftrag.findById(id);
    }

    @Transactional
    public Auftrag createAuftrag(String beschreibung) {
        Auftrag auftrag = new Auftrag(beschreibung, LocalDate.now());
        Auftrag.persist(auftrag);

        neuerAuftragEvent.fire(new NeuerAuftrag(auftrag.id));
        return auftrag;
    }

    @Transactional
    public Auftrag changeAuftrag(Auftrag auftrag) {
        Auftrag oldAuftrag = Auftrag.findById(auftrag.id);

        if (oldAuftrag == null) {
            return null;
        }

        oldAuftrag.setAuftragsbeschreibung(auftrag.getAuftragsbeschreibung());
        oldAuftrag.setEingangsdatum(auftrag.getEingangsdatum());
        oldAuftrag.setShip(auftrag.getShip());

        Auftrag.persist(oldAuftrag);
        return oldAuftrag;
    }

    public boolean deleteAuftrag(long id) {
        boolean deleted = Auftrag.deleteById(id);

        if(deleted){
            auftragStroniertEvent.fire(new AuftragStorniert());
        }
        return deleted;
    }
}
