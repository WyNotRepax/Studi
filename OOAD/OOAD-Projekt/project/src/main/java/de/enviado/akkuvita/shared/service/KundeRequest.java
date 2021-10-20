package de.enviado.akkuvita.shared.service;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import de.enviado.akkuvita.domain.entity.Kunde;
import de.enviado.akkuvita.shared.proxy.KundeProxy;

import java.util.List;

@Service(Kunde.class)
public interface KundeRequest extends RequestContext {
    InstanceRequest<KundeProxy, Void> persist();
    Request<KundeProxy> findKunde(Integer kundennummer);
    Request<List<KundeProxy>> findKunden(int start, int length);
    Request<List<KundeProxy>> findAllKunden();
    Request<Integer> sieben();
}
