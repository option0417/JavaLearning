package tw.com.wd;


import io.vertx.core.Launcher;
import tw.com.wd.vert.SimpleVertServer;


public class Executor {
    public static void main(String[] args) throws Exception {
        Launcher.executeCommand("run", SimpleVertServer.class.getName());
    }
}