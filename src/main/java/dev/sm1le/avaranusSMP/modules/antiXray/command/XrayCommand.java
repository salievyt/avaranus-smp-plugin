package dev.sm1le.avaranusSMP.modules.antiXray.command;

import dev.sm1le.avaranusSMP.modules.antiXray.score.XrayScoreCalculator;
import dev.sm1le.avaranusSMP.modules.antiXray.service.MiningService;
import dev.sm1le.avaranusSMP.modules.antiXray.session.MiningSession;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XrayCommand implements CommandExecutor {

    private final MiningService miningService;
    private final XrayScoreCalculator calculator;

    public XrayCommand(MiningService miningService, XrayScoreCalculator calculator) {
        this.miningService = miningService;
        this.calculator = calculator;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) return true;
        if (args.length != 1) return false;

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage("§cИгрок не найден");
            return true;
        }

        MiningSession s = miningService.get(target);
        double score = calculator.get(target);

        p.sendMessage("§eXRAY CHECK: " + target.getName());
        p.sendMessage("§7Score: §c" + (int) score);
        p.sendMessage("§7Diamonds: " + s.diamonds);
        p.sendMessage("§7Stone: " + s.stone);

        return true;
    }
}
