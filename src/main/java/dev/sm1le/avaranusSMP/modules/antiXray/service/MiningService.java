package dev.sm1le.avaranusSMP.modules.antiXray.service;

import dev.sm1le.avaranusSMP.modules.antiXray.session.MiningSession;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MiningService {

    private final Map<UUID, MiningSession> sessions = new HashMap<>();

    public MiningSession get(Player player) {
        return sessions.computeIfAbsent(player.getUniqueId(), k -> new MiningSession());
    }

    public void remove(Player player) {
        sessions.remove(player.getUniqueId());
    }
}