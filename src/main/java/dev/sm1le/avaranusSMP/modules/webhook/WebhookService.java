package dev.sm1le.avaranusSMP.modules.webhook;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WebhookService {

    private final String webhookUrl;
    private final JavaPlugin plugin;

    public WebhookService(JavaPlugin plugin) {
        this.plugin = plugin;
        this.webhookUrl = plugin.getConfig().getString("webhook.url");
    }

    public void send(WebhookType type, String description) {
        if (webhookUrl == null || webhookUrl.isEmpty()) return;

        try {
            URL url = new URL(webhookUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            String payload = """
            {
              "embeds": [{
                "title": "%s",
                "description": "%s",
                "color": %d
              }]
            }
            """.formatted(
                    type.getTitle(),
                    description.replace("\"", "'"),
                    type.getColor()
            );

            try (OutputStream os = con.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }

            con.getInputStream().close();
        } catch (Exception e) {
            plugin.getLogger().warning("[Webhook] Ошибка отправки: " + e.getMessage());
        }
    }
}