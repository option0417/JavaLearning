package tw.com.wd.vert.handler;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;

public class SimpleHeadersHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext context) {
        MultiMap headerMap = context.request().headers();
        showHeaders(headerMap);
    }

    private void showHeaders(MultiMap headerMap) {
        System.out.println("Show Headers");
        for (Map.Entry<String, String> headerEntry : headerMap) {
            String headerText = String.format("%s: %s", headerEntry.getKey(), headerEntry.getValue());
            System.out.println(headerText);
        }
    }
}
