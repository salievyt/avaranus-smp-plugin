package dev.sm1le.avaranusSMP.modules.webhook;

import dev.sm1le.avaranusSMP.modules.Module;
import dev.sm1le.avaranusSMP.modules.webhook.command.ReportCMD;
import dev.sm1le.avaranusSMP.modules.webhook.command.SuggestCMD;
import org.bukkit.plugin.java.JavaPlugin;

public class WebhookModule implements Module {

    private final JavaPlugin plugin;

    public WebhookModule(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        if (!plugin.getConfig().getBoolean("webhook.enabled")) return;

        WebhookService service = new WebhookService(plugin);

        plugin.getCommand("report").setExecutor(new ReportCMD(service));
        plugin.getCommand("suggest").setExecutor(new SuggestCMD(service));
    }

    @Override
    public void disable() {
        // пока пусто
    }
}