package tw.com.wd.vert;

import io.vertx.core.Launcher;

public class Executor {
    public static void main(String[] args) throws Exception {
        Launcher.executeCommand("run", SimpleVertServer.class.getName());
    }
}
