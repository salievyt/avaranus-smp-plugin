package dev.sm1le.avaranusSMP.modules.server.prohibition.lagmachines.service;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class LagMachineService {

    private final JavaPlugin plugin;

    public LagMachineService(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    // Проверка "редстоун часы / слишком частые сигналы"
    public boolean isRedstoneLagMachine(Block block) {
        Material type = block.getType();
        switch (type) {
            case REPEATER, COMPARATOR, REDSTONE_BLOCK, OBSERVER -> {
                // можно добавить таймер или счётчик тик
                return true;
            }
        }
        return false;
    }

    // Проверка "слишком много хопперов/воронок рядом"
    public boolean isHopperLagMachine(Block block) {
        Material type = block.getType();
        if (type == Material.HOPPER || type == Material.DROPPER || type == Material.DISPENSER) {
            // проверка окружения
            int count = 0;
            for (int x=-2; x<=2; x++) {
                for (int y=-2; y<=2; y++) {
                    for (int z=-2; z<=2; z++) {
                        Block b = block.getRelative(x,y,z);
                        if (b.getType() == Material.HOPPER || b.getType() == Material.DROPPER || b.getType() == Material.DISPENSER)
                            count++;
                    }
                }
            }
            return count > 8; // порог, можно менять
        }
        return false;
    }

    public void handle(Player player, Block block, String reason) {
        player.sendMessage("§cСервер заблокировал вашу конструкцию: " + reason);
        block.breakNaturally(); // ломаем блок
        plugin.getLogger().info("[LagMachines] Игрок " + player.getName() + " создал " + reason);
    }
}