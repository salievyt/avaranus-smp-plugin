package dev.sm1le.avaranusSMP.modules.server.setup.completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SetupTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 1){
            return List.of(
                    "start","set","module","player","staff"
            );
        }
        if (args[1].equalsIgnoreCase("module")){
            return List.of(
                    "player","server","staff","structure"
            );
        }
        return null;
    }
}
