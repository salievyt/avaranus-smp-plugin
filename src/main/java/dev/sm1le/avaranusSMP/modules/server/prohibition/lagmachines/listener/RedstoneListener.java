package dev.sm1le.avaranusSMP.modules.server.prohibition.lagmachines.listener;

import dev.sm1le.avaranusSMP.modules.server.prohibition.lagmachines.service.LagMachineService;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.entity.Player;

public class RedstoneListener implements Listener {

    private final LagMachineService service;

    public RedstoneListener(LagMachineService service) {
        this.service = service;
    }

    @EventHandler
    public void onRedstoneUpdate(BlockRedstoneEvent event) {
        Block block = event.getBlock();

        if (!service.isRedstoneLagMachine(block)) return;

        // найти игрока рядом (пример)
        block.getWorld().getNearbyEntities(block.getLocation(), 5,5,5)
                .stream()
                .filter(e -> e instanceof Player)
                .map(e -> (Player)e)
                .forEach(p -> service.handle(p, block, "Редстоун-машина"));
    }
}