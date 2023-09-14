package tw.com.wd.vert;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import tw.com.wd.vert.handler.SimpleBodyHandler;
import tw.com.wd.vert.handler.SimpleHeadersHandler;
import tw.com.wd.vert.handler.SimpleResponseHandler;

public class SimpleVertServer extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        //Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        router.route().handler(new SimpleHeadersHandler());
        router.route().handler(new SimpleBodyHandler());
        router.route().handler(new SimpleResponseHandler());

        vertx.createHttpServer().requestHandler(router).listen(9080).onSuccess(
                new Handler<HttpServer>() {
                    @Override
                    public void handle(HttpServer server) {
                        System.out.println("HTTP server started on port " + server.actualPort());
                    }
                });
    }
}
