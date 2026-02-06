package dev.sm1le.avaranusSMP.modules.staff.auth.listener;

import dev.sm1le.avaranusSMP.modules.staff.auth.AuthService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.player.*;

public class AuthListener implements Listener {

    private final AuthService service;

    public AuthListener(AuthService service) {
        this.service = service;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (service.isStaff(p)) {
            service.startAuth(p);
            p.sendMessage("§cВведите /key <пароль> для авторизации");
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!service.isAuthenticated(e.getPlayer())) e.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!service.isAuthenticated(e.getPlayer())) e.setCancelled(true);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (!service.isAuthenticated(e.getPlayer())
                && !e.getMessage().startsWith("/key")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (!service.isAuthenticated(e.getPlayer())) e.setCancelled(true);
    }
}