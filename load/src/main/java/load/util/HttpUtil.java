package load.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class.getSimpleName());
    private HttpUtil() {}

    public static void post(String url, String json) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .version(HttpClient.Version.HTTP_1_1)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .setHeader("Content-Type", "application/json")
                .build();
        try {
            HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (InterruptedException | IOException e) {
            logger.error("exception was thrown", e);
        }
    }
}
