package ru.iamdvz.wit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.iamdvz.wit.commands.AutoTabCompleter;
import ru.iamdvz.wit.commands.WITCommands;

public final class WIT extends JavaPlugin {
    private static WIT instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.getCommand("whoisthat").setExecutor(new WITCommands());
        this.getCommand("whoisthat").setTabCompleter(new AutoTabCompleter());

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new WITPlaceholders(this).register();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static WIT getInstance() {
        return instance;
    }

}
