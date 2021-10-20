package de.enviado.akkuvita.shared.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import de.enviado.akkuvita.domain.entity.Kunde;

@ProxyFor(Kunde.class)
public interface KundeProxy extends EntityProxy {
    Integer getKundennummer();
    void setKundennummer(Integer kundennummer);

    String getName();
    void setName(String name);

    String getFirma();
    void setFirma(String firma);
}
