package dev.sm1le.avaranusSMP.modules.antiXray.listener;

import dev.sm1le.avaranusSMP.modules.antiXray.punish.SoftPunishmentHandler;
import dev.sm1le.avaranusSMP.modules.antiXray.score.XrayScoreCalculator;
import dev.sm1le.avaranusSMP.modules.antiXray.service.MiningService;
import dev.sm1le.avaranusSMP.modules.antiXray.session.MiningSession;
import dev.sm1le.avaranusSMP.modules.webhook.WebhookService;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class XrayBlockListener implements Listener {

    private final MiningService miningService;
    private final XrayScoreCalculator calculator;
    private final SoftPunishmentHandler punishment;

    public XrayBlockListener(
            MiningService miningService,
            XrayScoreCalculator calculator,
            SoftPunishmentHandler punishment
    ) {
        this.miningService = miningService;
        this.calculator = calculator;
        this.punishment = punishment;

    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        MiningSession session = miningService.get(player);
        session.record(block.getType(), block.getLocation());

        double score = calculator.update(player, session);
        punishment.handle(event, score);

        if (score > 75) {
            Bukkit.getOnlinePlayers().stream()
                    .filter(p -> p.hasPermission("antixray.alert"))
                    .forEach(p ->
                            p.sendMessage("§c[XRAY] " + player.getName() + " score: " + (int) score)
                    );

        }
    }
}