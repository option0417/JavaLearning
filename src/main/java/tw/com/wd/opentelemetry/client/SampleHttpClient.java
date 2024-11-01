package tw.com.wd.opentelemetry.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

public class SampleHttpClient {
    public static void main(String[] args) {
        SampleHttpClient httpClient = new SampleHttpClient();

        // Perform request every 5s
        Thread t =
                new Thread(
                        () -> {
                            while (true) {
                                try {
                                    httpClient.makeRequest();
                                    Thread.sleep(5000);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        });
        t.start();
    }

    private void makeRequest() throws IOException, URISyntaxException {
        int port = 9080;
        URL url = new URL("http://127.0.0.1:" + port);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        int status = 0;
        StringBuilder content = new StringBuilder();

            try {
                // Process the request
                con.setRequestMethod("GET");
                status = con.getResponseCode();
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(con.getInputStream(), Charset.defaultCharset()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        // Output the result of the request
        System.out.println("Response Code: " + status);
        System.out.println("Response Msg: " + content);
    }
}
