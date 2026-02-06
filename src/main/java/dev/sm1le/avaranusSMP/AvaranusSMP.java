package dev.sm1le.avaranusSMP;

import dev.sm1le.avaranusSMP.modules.antiXray.AntiXrayModule;
import dev.sm1le.avaranusSMP.modules.Module;
import dev.sm1le.avaranusSMP.modules.player.economy.completer.EconomyTabCompleter;
import dev.sm1le.avaranusSMP.modules.player.point.completer.PointTabCompleter;
import dev.sm1le.avaranusSMP.modules.player.settings.adminka.command.AdminkaCMD;
import dev.sm1le.avaranusSMP.modules.player.settings.completer.SettingsTabCompleter;
import dev.sm1le.avaranusSMP.modules.player.stream.completer.StreamTabCompleter;
import dev.sm1le.avaranusSMP.modules.server.prohibition.exploit.ExploitModule;
import dev.sm1le.avaranusSMP.modules.server.prohibition.lagmachines.LagMachinesModule;
import dev.sm1le.avaranusSMP.modules.server.serverstatus.completer.ServerStatusTabCompleter;
import dev.sm1le.avaranusSMP.modules.server.setup.completer.SetupTabCompleter;
import dev.sm1le.avaranusSMP.modules.staff.playtime.completer.PlayTimeTabCompleter;
import dev.sm1le.avaranusSMP.modules.staff.punishment.command.*;
import dev.sm1le.avaranusSMP.modules.player.economy.command.EconomyCMD;
import dev.sm1le.avaranusSMP.modules.player.point.command.PointCMD;
import dev.sm1le.avaranusSMP.modules.player.settings.command.SettingsCMD;
import dev.sm1le.avaranusSMP.modules.server.serverstatus.command.ServerStatusCMD;
import dev.sm1le.avaranusSMP.modules.server.setup.command.SetupCMD;
import dev.sm1le.avaranusSMP.modules.staff.playtime.command.PlayTimeCMD;
import dev.sm1le.avaranusSMP.modules.staff.punishment.completer.MuteTabComplater;
import dev.sm1le.avaranusSMP.modules.staff.punishment.completer.UnBanTabComplater;
import dev.sm1le.avaranusSMP.modules.staff.punishment.completer.UnMuteTabCompleter;
import dev.sm1le.avaranusSMP.modules.staff.punishment.completer.WarnTabCompleter;
import dev.sm1le.avaranusSMP.modules.staff.spec.command.SpecCMD;
import dev.sm1le.avaranusSMP.modules.staff.spec.completer.SpecTabCompleter;
import dev.sm1le.avaranusSMP.modules.staff.work.command.WorkCMD;
import dev.sm1le.avaranusSMP.modules.player.stream.command.StreamCMD;
import dev.sm1le.avaranusSMP.modules.staff.work.completer.WorkTabCompleter;
import dev.sm1le.avaranusSMP.modules.structure.bank.command.BankCMD;
import dev.sm1le.avaranusSMP.modules.structure.bank.completer.BankTabCompleter;
import dev.sm1le.avaranusSMP.modules.structure.command.StructureCMD;
import dev.sm1le.avaranusSMP.modules.structure.completer.StructureTabCompleter;
import dev.sm1le.avaranusSMP.modules.structure.guardian.command.GuardianCMD;
import dev.sm1le.avaranusSMP.modules.structure.guardian.completer.GuardianTabCompleter;
import dev.sm1le.avaranusSMP.modules.webhook.WebhookModule;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;
import java.util.ArrayList;

import java.util.Objects;

public final class AvaranusSMP extends JavaPlugin {

    private static AvaranusSMP instance;

    // Модули
    private final List<Module> modules = new ArrayList<>();


    @Override
    public void onEnable() {
        saveDefaultConfig();
        modules.forEach(Module::enable);
        initCMD();
        initTabCompleter();
        registerModules();
        getLogger().info("AvaranusSMP успешно запущен");
    }

    @Override
    public void onDisable() {
        modules.forEach(Module::disable);
        getLogger().info("SMP OFF");
    }

    private void registerModules() {
        modules.add(new AntiXrayModule(this));
        modules.add(new WebhookModule(this));
        modules.add(new LagMachinesModule(this));
        modules.add(new ExploitModule(this));

        // modules.add(new EconomyModule(this));
        // modules.add(new StructureModule(this));
    }

    public static AvaranusSMP getInstance() {
        return instance;
    }

//    private void initModule(){
//        this.webhookModule = new WebhookModule(this);
//        webhookModule.enable();
//
//        this.antiXrayModule = new AntiXrayModule(this);
//        antiXrayModule.enable();
//    }



    public void initCMD(){
        Objects.requireNonNull(getCommand("economy")).setExecutor(new EconomyCMD());
        Objects.requireNonNull(getCommand("ban")).setExecutor(new BanCMD());
        Objects.requireNonNull(getCommand("guardian")).setExecutor(new GuardianCMD());
        Objects.requireNonNull(getCommand("mute")).setExecutor(new MuteCMD());
        Objects.requireNonNull(getCommand("unban")).setExecutor(new UnBanCMD());
        Objects.requireNonNull(getCommand("unmute")).setExecutor(new UnMuteCMD());
        Objects.requireNonNull(getCommand("warn")).setExecutor(new WarnCMD());
        Objects.requireNonNull(getCommand("serverstatus")).setExecutor(new ServerStatusCMD());
        Objects.requireNonNull(getCommand("setup")).setExecutor(new SetupCMD());
        Objects.requireNonNull(getCommand("spec")).setExecutor(new SpecCMD());
        Objects.requireNonNull(getCommand("work")).setExecutor(new WorkCMD());
        Objects.requireNonNull(getCommand("bank")).setExecutor(new BankCMD());
        Objects.requireNonNull(getCommand("playtime")).setExecutor(new PlayTimeCMD());
        Objects.requireNonNull(getCommand("point")).setExecutor(new PointCMD());
        Objects.requireNonNull(getCommand("settings")).setExecutor(new SettingsCMD());
        Objects.requireNonNull(getCommand("adminka")).setExecutor(new AdminkaCMD());
        Objects.requireNonNull(getCommand("stream")).setExecutor(new StreamCMD());
        Objects.requireNonNull(getCommand("structure")).setExecutor(new StructureCMD());
    }

    public void initTabCompleter(){
        Objects.requireNonNull(getCommand("economy")).setTabCompleter(new EconomyTabCompleter());
        Objects.requireNonNull(getCommand("guardian")).setTabCompleter(new GuardianTabCompleter());
        Objects.requireNonNull(getCommand("mute")).setTabCompleter(new MuteTabComplater());
        Objects.requireNonNull(getCommand("unban")).setTabCompleter(new UnBanTabComplater());
        Objects.requireNonNull(getCommand("unmute")).setTabCompleter(new UnMuteTabCompleter());
        Objects.requireNonNull(getCommand("warn")).setTabCompleter(new WarnTabCompleter());
        Objects.requireNonNull(getCommand("serverstatus")).setTabCompleter(new ServerStatusTabCompleter());
        Objects.requireNonNull(getCommand("setup")).setTabCompleter(new SetupTabCompleter());
        Objects.requireNonNull(getCommand("spec")).setTabCompleter(new SpecTabCompleter());
        Objects.requireNonNull(getCommand("work")).setTabCompleter(new WorkTabCompleter());
        Objects.requireNonNull(getCommand("bank")).setTabCompleter(new BankTabCompleter());
        Objects.requireNonNull(getCommand("playtime")).setTabCompleter(new PlayTimeTabCompleter());
        Objects.requireNonNull(getCommand("point")).setTabCompleter(new PointTabCompleter());
        Objects.requireNonNull(getCommand("settings")).setTabCompleter(new SettingsTabCompleter());
        Objects.requireNonNull(getCommand("stream")).setTabCompleter(new StreamTabCompleter());
        Objects.requireNonNull(getCommand("structure")).setTabCompleter(new StructureTabCompleter());
    }
}
