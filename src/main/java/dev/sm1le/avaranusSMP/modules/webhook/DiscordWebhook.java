package dev.sm1le.avaranusSMP.modules.webhook;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DiscordWebhook {

    private final String url;

    public DiscordWebhook(String url) {
        this.url = url;
    }

    public void send(String json) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }

            con.getInputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}