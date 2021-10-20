package de.hsos.swa.pizza4me.gateway;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import de.hsos.swa.pizza4me.control.BestellungService;
import de.hsos.swa.pizza4me.control.KundeService;
import de.hsos.swa.pizza4me.entity.Bestellung;
import de.hsos.swa.pizza4me.entity.Kunde;

@RequestScoped
public class BestellungRepository implements BestellungService{

    @Inject
    KundeService kundeService;

    @Override
    @Transactional(TxType.REQUIRED)
    public Bestellung bestellungAnlegen(Long kundenid) {
        Kunde kunde = kundeService.kundeAbfragen(kundenid);
        if(kunde == null){
            return null;
        }
        Bestellung bestellung = new Bestellung();
        bestellung.persist();
        kunde.getBestellungen().add(bestellung);
        return bestellung;
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public Collection<Bestellung> bestellungenAbfragen(Long kundenid) {
        // Kunde kunde = kundeService.kundeAbfragen(kundenid);
        // if(kunde == null){
        //     return null;
        // }
        // return kunde.getBestellungen();
        
        return Bestellung.list("SELECT K.bestellungen FROM Kunde K WHERE K.id = ?1", kundenid);
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public Bestellung bestellungAbfragen(long kundenid, long bestellungId) {
        return Bestellung.find("SELECT B FROM Kunde K JOIN K.bestellungen B WHERE B.id = ?2 AND K.id = ?1", kundenid, bestellungId).firstResult();
    }
    

}