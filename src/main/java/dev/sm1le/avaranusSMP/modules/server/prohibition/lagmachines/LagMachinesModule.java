package dev.sm1le.avaranusSMP.modules.server.prohibition.lagmachines;

import dev.sm1le.avaranusSMP.modules.Module;
import dev.sm1le.avaranusSMP.modules.server.prohibition.lagmachines.listener.HopperListener;
import dev.sm1le.avaranusSMP.modules.server.prohibition.lagmachines.listener.RedstoneListener;
import dev.sm1le.avaranusSMP.modules.server.prohibition.lagmachines.service.LagMachineService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LagMachinesModule implements Module {

    private final JavaPlugin plugin;
    private final LagMachineService service;

    public LagMachinesModule(JavaPlugin plugin) {
        this.plugin = plugin;
        this.service = new LagMachineService(plugin);
    }

    @Override
    public void enable() {
        // Слушатели
        Bukkit.getPluginManager().registerEvents(new RedstoneListener(service), plugin);
        Bukkit.getPluginManager().registerEvents(new HopperListener(service), plugin);

        plugin.getLogger().info("[LagMachines] Модуль включен");
    }

    @Override
    public void disable() {
        plugin.getLogger().info("[LagMachines] Модуль выключен");
    }
}
