package de.enviado.akkuvita.shared.service;

import com.google.web.bindery.requestfactory.shared.*;
import de.enviado.akkuvita.domain.entity.Akku;
import de.enviado.akkuvita.shared.proxy.AkkuEventProxy;
import de.enviado.akkuvita.shared.proxy.AkkuProxy;
import de.enviado.akkuvita.shared.proxy.AusmusterungsEventProxy;

import java.util.List;


@Service(Akku.class)
@ExtraTypes(AusmusterungsEventProxy.class)
public interface AkkuRequest extends RequestContext {
    InstanceRequest<AkkuProxy, Void> persist();
    Request<AkkuProxy> findAkku(String seriennummer);
    Request<List<AkkuProxy>> findAkkus(int start, int length);
    Request<List<AkkuProxy>> findAllAkkus();
}
