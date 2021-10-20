package de.enviado.akkuvita.client;

import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;
import de.enviado.akkuvita.shared.AkkuVitaRequestFactory;
import de.enviado.akkuvita.shared.proxy.AkkuProxy;
import de.enviado.akkuvita.shared.proxy.KundeProxy;

import java.util.List;

public class KundenDataProvider extends ProxyDataProvider<KundeProxy> {


    KundenDataProvider(AkkuVitaRequestFactory requestFactory) {
        super(requestFactory);
    }

    @Override
    protected void onRangeChanged(HasData<KundeProxy> display) {
        Range range = display.getVisibleRange();
        requestFactory.kundeRequest().findKunden(range.getStart(), range.getLength()).fire(new Receiver<List<KundeProxy>>() {
            @Override
            public void onSuccess(List<KundeProxy> response) {
                display.setRowData(range.getStart(), response);
                display.setRowCount(range.getStart() + response.size(), response.size() < range.getLength());
            }
        });
    }
}
