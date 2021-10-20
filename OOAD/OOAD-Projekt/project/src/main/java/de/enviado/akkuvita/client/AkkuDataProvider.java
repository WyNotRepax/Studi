package de.enviado.akkuvita.client;

import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;
import de.enviado.akkuvita.shared.AkkuVitaRequestFactory;
import de.enviado.akkuvita.shared.proxy.AkkuProxy;

import java.util.List;

public class AkkuDataProvider extends ProxyDataProvider<AkkuProxy> {

    AkkuDataProvider(AkkuVitaRequestFactory requestFactory) {
        super(requestFactory);
    }

    @Override
    protected void onRangeChanged(HasData<AkkuProxy> display) {
        Range range = display.getVisibleRange();
        requestFactory.akkuRequest().findAkkus(range.getStart(), range.getLength()).fire(new Receiver<List<AkkuProxy>>() {
            @Override
            public void onSuccess(List<AkkuProxy> response) {
                display.setRowData(range.getStart(), response);
                display.setRowCount(range.getStart() + response.size(), response.size() < range.getLength());
            }
        });
    }
}
