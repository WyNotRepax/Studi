package de.enviado.akkuvita.client;

import com.google.gwt.user.client.ui.SuggestOracle;
import de.enviado.akkuvita.shared.proxy.AkkuProxy;

import java.util.List;
import java.util.stream.Collectors;

public class AkkuSuggestOracle extends SuggestOracle {

    private List<AkkuProxy> akkuProxyList;

    public void setAkkuProxyList(List<AkkuProxy> akkuProxyList) {
        this.akkuProxyList = akkuProxyList;
    }

    @Override
    public void requestSuggestions(Request request, Callback callback) {
        if(akkuProxyList == null){
            callback.onSuggestionsReady(request,new Response());
        }
        Response response = new Response();
        response.setSuggestions(
                akkuProxyList.stream().filter(s ->
                s.getSeriennummer().contains(request.getQuery())
                ).map(AkkuSuggestion::new).collect(Collectors.toList())
            );
        callback.onSuggestionsReady(request,response);
    }

    private static class AkkuSuggestion implements Suggestion {

        private final String string;

        AkkuSuggestion(AkkuProxy proxy){
            string = proxy.getSeriennummer();
        }

        @Override
        public String getDisplayString() {
            return string;
        }

        @Override
        public String getReplacementString() {
            return string;
        }
    }
}
