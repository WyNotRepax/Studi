package de.hsos.swa.pizza4me.gateway;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import de.hsos.swa.pizza4me.control.BestellpostenService;
import de.hsos.swa.pizza4me.control.BestellungService;
import de.hsos.swa.pizza4me.control.PizzaService;
import de.hsos.swa.pizza4me.entity.Bestellposten;
import de.hsos.swa.pizza4me.entity.Bestellung;
import de.hsos.swa.pizza4me.entity.Pizza;

@RequestScoped
public class BestellpostenRepository implements BestellpostenService {

    @Inject
    BestellungService bestellungService;

    @Inject
    PizzaService pizzaService;

    @Override
    @Transactional(TxType.REQUIRED)
    public Bestellposten bestellpostenAnlegen(long kundenId, long bestellungId, long pizzaId, int menge) {
        Bestellung bestellung = bestellungService.bestellungAbfragen(kundenId, bestellungId);
        Pizza pizza = pizzaService.getPizza(pizzaId);
        if (bestellung == null || pizza == null) {
            return null;
        }
        Bestellposten bestellposten = new Bestellposten(pizza, menge);
        bestellposten.persist();
        bestellung.getBestellposten().add(bestellposten);
        return bestellposten;
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public Bestellposten bestellpostenAendern(long kundenId, long bestellungId, long bestellpostenId, int menge) {
        Bestellung bestellung = bestellungService.bestellungAbfragen(kundenId, bestellungId);
        if (bestellung == null) {
            return null;
        }
        Bestellposten bestellposten = bestellung.getBestellposten().stream().filter(b -> b.id == bestellpostenId).findFirst().orElse(null);
        if(bestellposten == null){
            return null;
        }
        bestellposten.setMenge(menge);
        return bestellposten;
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public boolean bestellpostenLoeschen(long kundenId, long bestellungId, long bestellpostenId) {
        Bestellung bestellung = bestellungService.bestellungAbfragen(kundenId, bestellungId);
        if (bestellung == null) {
            return false;
        }
        Bestellposten bestellposten = bestellung.getBestellposten().stream().filter(b -> b.id == bestellpostenId).findFirst().orElse(null);
        if(bestellposten == null){
            return false;
        }
        bestellposten.delete();
        bestellung.getBestellposten().remove(bestellposten);
        return true;
    }

}
