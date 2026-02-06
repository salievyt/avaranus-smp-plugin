package dev.sm1le.avaranusSMP.modules.webhook.command;

import dev.sm1le.avaranusSMP.modules.webhook.WebhookService;
import dev.sm1le.avaranusSMP.modules.webhook.WebhookType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ReportCMD implements CommandExecutor {

    private final WebhookService service;

    public ReportCMD(WebhookService service) {
        this.service = service;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) return true;
        if (args.length < 2) {
            p.sendMessage("§cИспользование: /report <ник> <причина>");
            return true;
        }

        String target = args[0];
        String reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        service.send(
                WebhookType.REPORT,
                "🚨 Новый репорт",
                p,
                "На игрока **" + target + "**\nПричина: " + reason
        );

        p.sendMessage("§aРепорт отправлен. Спасибо!");
        return true;
    }
}