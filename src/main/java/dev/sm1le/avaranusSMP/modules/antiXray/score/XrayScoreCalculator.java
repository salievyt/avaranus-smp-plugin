package dev.sm1le.avaranusSMP.modules.antiXray.score;

import dev.sm1le.avaranusSMP.modules.antiXray.session.MiningSession;
import dev.sm1le.avaranusSMP.modules.antiXray.util.XrayUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class XrayScoreCalculator {

    private final Map<UUID, Double> scores = new HashMap<>();

    public double update(Player player, MiningSession session) {
        double score = scores.getOrDefault(player.getUniqueId(), 0.0);

        if (session.diamonds > 0) {
            score += session.diamonds * 8;
            score += (double) session.diamonds / Math.max(1, session.stone) * 120;
        }

        if (XrayUtils.hasSharpTurns(session.path))
            score += 15;

        if (XrayUtils.foundWithoutTunnel(session.path))
            score += 20;

        score *= 0.96; // спад

        scores.put(player.getUniqueId(), score);
        return score;
    }

    public double get(Player player) {
        return scores.getOrDefault(player.getUniqueId(), 0.0);
    }
}