package dev.sm1le.avaranusSMP.modules.webhook.command;

import dev.sm1le.avaranusSMP.modules.webhook.WebhookService;
import dev.sm1le.avaranusSMP.modules.webhook.WebhookType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuggestCMD implements CommandExecutor {

    private final WebhookService service;

    public SuggestCMD(WebhookService service) {
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) return true;
        if (args.length < 1) {
            p.sendMessage("§cИспользование: /suggest <предложение>");
            return true;
        }

        String message = String.join(" ", args);

        service.send(
                WebhookType.SUGGEST,
                "💡 Новое предложение",
                p,
                message
        );

        p.sendMessage("§aСпасибо за идею! Мы её рассмотрим ❤️");
        return true;
    }
}