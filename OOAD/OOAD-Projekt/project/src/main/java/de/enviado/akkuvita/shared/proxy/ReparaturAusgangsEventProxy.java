package de.enviado.akkuvita.shared.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import de.enviado.akkuvita.domain.entity.AkkuReparaturAusgangsEvent;

@ProxyFor(AkkuReparaturAusgangsEvent.class)
public interface ReparaturAusgangsEventProxy extends AkkuEventProxy {
    String getNotiz();
    void setNotiz(String notiz);
}
