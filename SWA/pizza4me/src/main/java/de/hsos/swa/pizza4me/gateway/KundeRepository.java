package de.hsos.swa.pizza4me.gateway;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import de.hsos.swa.pizza4me.control.KundeService;
import de.hsos.swa.pizza4me.entity.Adresse;
import de.hsos.swa.pizza4me.entity.Kunde;

/**
 * @author Chrisoph Freimuth, Benno Steinkamp (pair programming)
 */
@RequestScoped
public class KundeRepository implements KundeService {

    @Override
    @Transactional(TxType.REQUIRED)
    public Collection<Kunde> kundenAbfragen() {
        return Kunde.findAll().list();
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public Kunde kundenAnlegen() {
        Kunde kunde = new Kunde();
        kunde.persist();
        return kunde;
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public void adresseAnlegen(Long kundennummer, Adresse adr) {
        Kunde kunde = this.kundeAbfragen(kundennummer);
        if (kunde == null) {
            return;
        }
        if (kunde.getAdresse() == null) {
            kunde.setAdresse(adr);
        }
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public void adresseAendern(Long kundennummer, Adresse adr) {
        Kunde kunde = this.kundeAbfragen(kundennummer);
        if (kunde == null) {
            return;
        }
        if (kunde.getAdresse() != null) {
            kunde.setAdresse(adr);
        }
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public Adresse adresseAbfragen(Long kundennummer) {
        Kunde kunde = this.kundeAbfragen(kundennummer);
        if (kunde == null) {
            return null;
        }
        return kunde.getAdresse();
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public boolean adresseLoeschen(Long kundennummer) {
        Kunde kunde = this.kundeAbfragen(kundennummer);
        if (kunde == null || kunde.getAdresse() == null) {
            return false;
        }
        kunde.setAdresse(null);
        return true;
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public Kunde kundeAbfragen(long id) {
        return Kunde.findById(id);
    }

}
