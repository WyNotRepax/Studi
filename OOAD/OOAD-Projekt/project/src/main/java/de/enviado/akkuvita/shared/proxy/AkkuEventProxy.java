package de.enviado.akkuvita.shared.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import de.enviado.akkuvita.domain.entity.AkkuEvent;

import java.util.Date;

@ProxyFor(AkkuEvent.class)
public interface AkkuEventProxy extends EntityProxy {

    Date getDate();

    void setDate(Date date);

    AkkuProxy getAkku();
    void setAkku(AkkuProxy akku);


}
