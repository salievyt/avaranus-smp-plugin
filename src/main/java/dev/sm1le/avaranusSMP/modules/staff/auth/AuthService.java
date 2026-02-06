package dev.sm1le.avaranusSMP.modules.staff.auth;


import dev.sm1le.avaranusSMP.modules.webhook.WebhookService;
import dev.sm1le.avaranusSMP.modules.webhook.WebhookType;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.net.InetAddress;
import java.util.*;

public class AuthService {

    private final JavaPlugin plugin;
    private final File file;
    private final YamlConfiguration config;
    private final WebhookService webhook;

    private final Set<UUID> unauthenticated = new HashSet<>();
    private final Map<String, Integer> attempts = new HashMap<>();

    public AuthService(JavaPlugin plugin) {
        this.plugin = plugin;
        this.webhook = new WebhookService(plugin);
        file = new File(plugin.getDataFolder(), "staffdata.yml");
        if (!file.exists()) plugin.saveResource("staffdata.yml", false);
        config = YamlConfiguration.loadConfiguration(file);
    }

    public boolean isStaff(Player player) {
        return config.contains("staff." + player.getName());
    }

    public void startAuth(Player player) {
        unauthenticated.add(player.getUniqueId());

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (unauthenticated.contains(player.getUniqueId())) {
                fail(player);
            }
        }, 20L * 60);
    }

    public boolean isAuthenticated(Player player) {
        return !unauthenticated.contains(player.getUniqueId());
    }

    public void authenticate(Player player, String password) {
        String path = "staff." + player.getName() + ".password";
        String real = config.getString(path);

        if (real != null && real.equals(password)) {
            unauthenticated.remove(player.getUniqueId());
            attempts.remove(player.getAddress().getAddress().getHostAddress());
            player.sendMessage("§aАвторизация успешна");
            webhook.send(
                    WebhookType.AUTH_SUCCESS,
                    "Администратор **" + player.getName() + "** успешно прошёл авторизацию\n"
                            + "IP: `" + player.getAddress().getAddress().getHostAddress() + "`"
            );
        } else {
            fail(player);
        }
    }

    private void fail(Player player) {
        String ip = player.getAddress().getAddress().getHostAddress();
        int count = attempts.getOrDefault(ip, 0) + 1;
        attempts.put(ip, count);

        if (count >= 4) {
            Bukkit.getBanList(BanList.Type.IP)
                    .addBan(ip, "Подозрение на взлом аккаунта администратора", null, "AvaranusSMP");

            sendWebhook(player.getName(), ip);
            player.kickPlayer("§cВы заблокированы\n§7Подозрение на взлом аккаунта администратора");
        } else {
            player.kickPlayer("§cАвторизация не пройдена (" + count + "/3)");
        }

        unauthenticated.remove(player.getUniqueId());
    }

    private void sendWebhook(String name, String ip) {
        webhook.send(
                WebhookType.AUTH_ALERT,
                "❗ **Подозрение на взлом аккаунта администратора**\n\n"
                        + "**Ник:** " + name + "\n"
                        + "**IP:** `" + ip + "`\n"
                        + "**Действие:** IP-BAN\n"
                        + "**Причина:** не пройдена авторизация 3 раза"
        );
    }
}