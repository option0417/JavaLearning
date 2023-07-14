package tw.com.wd.etcd;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.kv.PutResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public class KVService {
    private static final String ETCD_SINGLE_ENDPOINT = "http://127.0.0.1:2379";
    private KV kvClient;

    public KVService(KV kvClient) {
        super();
        this.kvClient = kvClient;
    }

    public CompletableFuture<PutResponse> put(byte[] key, byte[] value) throws Exception {
        CompletableFuture<PutResponse> respFuture = kvClient.put(ByteSequence.from(key), ByteSequence.from(value));
        return respFuture;
    }

    public byte[] get(byte[] key) throws Exception {

        CompletableFuture<GetResponse> respFuture = kvClient.get(ByteSequence.from(key));
        GetResponse getResp = respFuture.get();

        List<KeyValue> kvlist = getResp.getKvs();
        System.out.printf("Size of KVList: %d\n", kvlist.size());

        return kvlist.get(0).getValue().getBytes();
    }

    public static void main(String[] args) throws Exception {
        // create client using endpoints
        Client client = Client
            .builder()
            .endpoints(ETCD_SINGLE_ENDPOINT)
            .build();
        String etcdKey = "Update";

        KVService kvService = new KVService(client.getKVClient());

        System.out.println("Start put");
        CompletableFuture<PutResponse> respFuture = null;
        for (int cnt = 0; cnt < 10; cnt++) {
            respFuture = kvService.put(etcdKey.getBytes(), ("v" + cnt).getBytes());
        }
        while (!respFuture.isDone());
        System.out.println("put done");

        client.close();

    }
}
