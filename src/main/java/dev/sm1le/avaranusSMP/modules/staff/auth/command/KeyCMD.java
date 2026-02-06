package dev.sm1le.avaranusSMP.modules.staff.auth.command;

import dev.sm1le.avaranusSMP.modules.staff.auth.AuthService;
import dev.sm1le.avaranusSMP.modules.webhook.WebhookService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KeyCMD implements CommandExecutor {

    private final AuthService service;

    public KeyCMD(AuthService service) {
        this.service = service;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) return false;

        if (args.length != 1) {
            p.sendMessage("§cИспользуй: /key <пароль>");
            return true;
        }

        service.authenticate(p, args[0]);
        return true;
    }
}