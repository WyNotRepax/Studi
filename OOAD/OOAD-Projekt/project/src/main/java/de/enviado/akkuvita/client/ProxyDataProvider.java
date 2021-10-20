package de.enviado.akkuvita.client;

import com.google.gwt.view.client.AsyncDataProvider;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import de.enviado.akkuvita.shared.AkkuVitaRequestFactory;

public abstract class ProxyDataProvider<T> extends AsyncDataProvider<T> {
    protected final AkkuVitaRequestFactory requestFactory;
    ProxyDataProvider(AkkuVitaRequestFactory requestFactory){
        this.requestFactory = requestFactory;
    }

}
