package dev.sm1le.avaranusSMP.modules.antiXray;

import dev.sm1le.avaranusSMP.modules.Module;
import dev.sm1le.avaranusSMP.modules.antiXray.command.XrayCommand;
import dev.sm1le.avaranusSMP.modules.antiXray.listener.XrayBlockListener;
import dev.sm1le.avaranusSMP.modules.antiXray.punish.SoftPunishmentHandler;
import dev.sm1le.avaranusSMP.modules.antiXray.score.XrayScoreCalculator;
import dev.sm1le.avaranusSMP.modules.antiXray.service.MiningService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class AntiXrayModule implements Module {

    private final JavaPlugin plugin;
    private final MiningService miningService;
    private final XrayScoreCalculator scoreCalculator;
    private final SoftPunishmentHandler punishmentHandler;

    public AntiXrayModule(JavaPlugin plugin) {
        this.plugin = plugin;
        this.miningService = new MiningService();
        this.scoreCalculator = new XrayScoreCalculator();
        this.punishmentHandler = new SoftPunishmentHandler();
    }


    @Override
    public void enable() {
        Bukkit.getPluginManager().registerEvents(
                new XrayBlockListener(miningService, scoreCalculator, punishmentHandler),
                plugin
        );

        Objects.requireNonNull(plugin.getCommand("xray"))
                .setExecutor(new XrayCommand(miningService, scoreCalculator));
    }

    @Override
    public void disable() {
        /*
        Тут пусто так как нехуй сувать сюда свой нос
         */
    }
}