package tw.com.wd.vert.handler;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class SimpleResponseHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("resp_code", 0);
        jsonObject.put("resp_msg", "ok");

        context.json(jsonObject);
    }
}
