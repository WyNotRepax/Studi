package de.enviado.akkuvita.shared.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import de.enviado.akkuvita.domain.entity.Akku;
import de.enviado.akkuvita.domain.entity.AkkuEvent;
import de.enviado.akkuvita.shared.AkkuDefekt;

import java.util.Date;
import java.util.Set;

@ProxyFor(Akku.class)
public interface AkkuProxy extends EntityProxy {

    String getSeriennummer();
    void setSeriennummer(String seriennummer);

    Date getProduktionsdatum();
    void setProduktionsdatum(Date produktionsdatum);

    Integer getReperaturanzahl();
    void setReperaturanzahl(Integer reperaturanzahl);

}
