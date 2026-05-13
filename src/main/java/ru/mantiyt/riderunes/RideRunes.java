package ru.mantiyt.riderunes;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.mantiyt.riderunes.command.GiveCommand;
import ru.mantiyt.riderunes.command.TabCompleter;
import ru.mantiyt.riderunes.listener.TotemUseListener;
import ru.mantiyt.riderunes.listener.AnvilListener;

public final class RideRunes extends JavaPlugin {
    private static RideRunes instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("riderunes").setExecutor(new GiveCommand());
        getCommand("riderunes").setTabCompleter(new TabCompleter());
        Bukkit.getPluginManager().registerEvents(new TotemUseListener(), this);
        Bukkit.getPluginManager().registerEvents(new AnvilListener(), this);
    }

    @Override
    public void onDisable() {}

    public static RideRunes getInstance() {
        return instance;
    }
}