package tw.com.wd.vert;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.ext.web.Router;

public class HelloServer extends AbstractVerticle {

    public static void main(String[] args) {
        Launcher.executeCommand("run", HelloServer.class.getName());
    }

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        router.route().handler(routingContext -> {
            routingContext.response().putHeader("content-type", "text/html").end("Hello World!");
        });

        vertx.createHttpServer().requestHandler(router).listen(8080);
    }
}
