package tw.com.wd.vert.handler;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.ext.web.RequestBody;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;

public class SimpleBodyHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext context) {
        showQueryParams(context.queryParams());
        showRequestBody(context.body());
    }

    private void showQueryParams(MultiMap queryParamsMap) {
        System.out.println("Show QueryParams");
        for (Map.Entry<String, String> queryParamsEntry : queryParamsMap) {
            String headerText = String.format("%s: %s", queryParamsEntry.getKey(), queryParamsEntry.getValue());
            System.out.println(headerText);
        }
    }

    private void showRequestBody(RequestBody requestBody) {
        System.out.println("Show RequestBody");
        System.out.println(requestBody.asString());
    }
}
