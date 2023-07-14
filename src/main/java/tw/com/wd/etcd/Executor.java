package tw.com.wd.etcd;

import io.etcd.jetcd.Client;


public class Executor {
    private static final String ETCD_SINGLE_ENDPOINT = "http://127.0.0.1:2379";
    private static final String ETCD_NODE_0 = "http://127.0.0.1:2370";
    private static final String ETCD_NODE_1 = "http://127.0.0.1:2380";
    private static final String ETCD_NODE_2 = "http://127.0.0.1:2390";
    private static final String KEY_01 = "k01";

    public static void main(String[] args) throws Exception {
        // create client using endpoints
        Client client = Client
            .builder()
            .endpoints(ETCD_SINGLE_ENDPOINT)
            .build();
        String etcdKey = "Update";

        KVService kvService = new KVService(client.getKVClient());

        System.out.println("Start put");
        for (int cnt = 0; cnt < 10; cnt++) {
            kvService.put(etcdKey.getBytes(), ("v" + cnt).getBytes());
        }
        System.out.println("put done");
        client.close();

    }
}
