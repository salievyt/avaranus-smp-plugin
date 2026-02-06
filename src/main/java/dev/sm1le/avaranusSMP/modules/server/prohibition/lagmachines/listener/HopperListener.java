package dev.sm1le.avaranusSMP.modules.server.prohibition.lagmachines.listener;

import dev.sm1le.avaranusSMP.modules.server.prohibition.lagmachines.service.LagMachineService;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class HopperListener implements Listener {

    private final LagMachineService service;

    public HopperListener(LagMachineService service) {
        this.service = service;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        if (service.isHopperLagMachine(block)) {
            service.handle(player, block, "Ферма с хопперами/воронками");
        }
    }
}