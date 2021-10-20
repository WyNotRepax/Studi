package de.enviado.akkuvita.shared.service;

import com.google.web.bindery.requestfactory.shared.*;
import de.enviado.akkuvita.domain.entity.AkkuEvent;
import de.enviado.akkuvita.shared.proxy.*;

import java.util.List;

@Service(AkkuEvent.class)
@ExtraTypes({
        AkkuPruefungsEventProxy.class,
        ReparaturAusgangsEventProxy.class,
        ReparaturEingangsEventProxy.class,
        AusmusterungsEventProxy.class,
        KundeProxy.class,
        AkkuProxy.class
})
public interface AkkuEventRequest extends RequestContext {
    InstanceRequest<AkkuEventProxy, Void> persist();
    Request<List<AkkuEventProxy>> findAkkuEvent(AkkuProxy akkuProxy);
    Request<List<AkkuEventProxy>> findAkkuEvent(KundeProxy kundeProxy);
}
