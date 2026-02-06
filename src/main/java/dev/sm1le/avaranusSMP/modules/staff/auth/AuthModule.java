package dev.sm1le.avaranusSMP.modules.staff.auth;

import dev.sm1le.avaranusSMP.modules.Module;
import dev.sm1le.avaranusSMP.modules.staff.auth.command.KeyCMD;
import dev.sm1le.avaranusSMP.modules.staff.auth.listener.AuthListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AuthModule implements Module {

    private final JavaPlugin plugin;
    private AuthService authService;

    public AuthModule(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() {
        authService = new AuthService(plugin);

        Bukkit.getPluginManager().registerEvents(new AuthListener(authService), plugin);
        plugin.getCommand("key").setExecutor(new KeyCMD(authService));

        plugin.getLogger().info("[Auth] Модуль авторизации персонала включён");
    }

    @Override
    public void disable() {
        plugin.getLogger().info("[Auth] Модуль авторизации персонала выключен");
    }
}