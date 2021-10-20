package de.enviado.akkuvita.shared.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import de.enviado.akkuvita.domain.entity.AkkuPruefungsEvent;
import de.enviado.akkuvita.shared.AkkuDefekt;

@ProxyFor(AkkuPruefungsEvent.class)
public interface AkkuPruefungsEventProxy extends AkkuEventProxy {

    String getNotiz();

    void setNotiz(String notiz);

    Float getKapazitaet();

    void setKapazitaet(Float kapazitaet);

    Integer getTicketnr();

    void setTicketnr(Integer ticketnr);

    Integer getLadezyklen();

    void setLadezyklen(Integer ladezyklen);

    void setKunde(KundeProxy kunde);

    AkkuDefekt getDefekt();

    void setDefekt(AkkuDefekt defekt);
}
