package load.util;

import load.service.LoadOperationService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpUtil {
    private static final Logger logger = Logger.getLogger(HttpUtil.class.getSimpleName());
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
            logger.log(Level.SEVERE, "exception was thrown", e);
        }
    }
}