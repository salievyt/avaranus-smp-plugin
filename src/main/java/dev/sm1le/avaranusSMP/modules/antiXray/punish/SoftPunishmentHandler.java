package dev.sm1le.avaranusSMP.modules.antiXray.punish;

import org.bukkit.event.block.BlockBreakEvent;

public class SoftPunishmentHandler {

    public void handle(BlockBreakEvent event, double score) {
        if (score < 60) return;

        if (Math.random() < 0.35) {
            event.setDropItems(false);
        }
    }
}