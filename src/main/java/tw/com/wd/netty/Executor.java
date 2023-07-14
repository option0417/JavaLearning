package tw.com.wd.netty;

import com.google.gson.Gson;
import io.netty.channel.Channel;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import tw.com.wd.api.impl.HelloImpl;
import tw.com.wd.obj.TestObj;
import tw.com.wd.server.NettyServer;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;


public class Executor {
    private static final String HOST = "localhost";
    private static final int PORT = 8090;

    public static void main( String[] args ) throws Exception {
        String json = "{\"resp\":{}}";

        TestObj obj = new Gson().fromJson(json, TestObj.class);

        if (obj != null) {
            System.out.println("TestObj != null");
            if (obj.getResp() != null) {
                System.out.println("getResp != null");
                System.out.println(obj.getResp().getBranchList());
            } else {
                System.out.println("getResp == null");
            }
        } else {
            System.out.println("TestObj == null");
        }

        //startJerseyWithNetty();
    }

    private static void startNettyServer() throws Exception {
        NettyServer server = new NettyServer(HOST, PORT);
        server.start();
    }

    private static void startJerseyWithNetty() {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9080).build();
        ResourceConfig resourceConfig = new ResourceConfig(HelloImpl.class);

        Channel server = NettyHttpContainerProvider.createServer(baseUri, resourceConfig, false);

        System.out.printf("IsActive: %s\n", server.isActive());
        System.out.printf("isOpen: %s\n", server.isOpen());
        System.out.printf("isRegistered: %s\n", server.isRegistered());
        System.out.printf("isWritable: %s\n", server.isWritable());

        System.out.printf("Server started.\n");
    }
}
