package de.enviado.akkuvita.client;

import com.google.gwt.user.client.ui.SuggestOracle;
import de.enviado.akkuvita.shared.proxy.KundeProxy;

import java.util.List;
import java.util.stream.Collectors;

public class KundeSuggestOracle extends SuggestOracle {

    private List<KundeProxy> kunden;

    public void setKunden(List<KundeProxy> kunden) {
        this.kunden = kunden;
    }

    @Override
    public void requestSuggestions(Request request, Callback callback) {
        if (kunden == null) {
            callback.onSuggestionsReady(request, new Response());
        }
        Response response = new Response();
        response.setSuggestions(
                kunden.stream().map(KundeSuggestion::new).filter(kundeSuggestion -> kundeSuggestion.isValidFor(request.getQuery())).collect(Collectors.toList())
        );
        callback.onSuggestionsReady(request, response);
    }

    private static class KundeSuggestion implements Suggestion {

        private final KundeProxy kunde;

        KundeSuggestion(KundeProxy kunde) {
            this.kunde = kunde;
        }

        public boolean isValidFor(String query){
            return kunde.getName().contains(query) || kunde.getFirma().contains(query) || kunde.getKundennummer().toString().contains(query);
        }

        @Override
        public String getDisplayString() {
            return kunde.getKundennummer().toString() + " " + kunde.getName() + " " + kunde.getFirma();
        }

        @Override
        public String getReplacementString() {
            return kunde.getKundennummer().toString();
        }
    }
}
