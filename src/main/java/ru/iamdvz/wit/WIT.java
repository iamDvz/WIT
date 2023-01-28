package ru.iamdvz.wit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.iamdvz.core.DivizionCore;
import ru.iamdvz.core.utils.MythicMobUtil;
import ru.iamdvz.wit.commands.AutoTabCompleter;
import ru.iamdvz.wit.commands.WITCommands;
import java.util.Objects;

public final class WIT extends JavaPlugin {
    private static WIT instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        Objects.requireNonNull(this.getCommand("whoisthat")).setExecutor(new WITCommands());
        Objects.requireNonNull(this.getCommand("whoisthat")).setTabCompleter(new AutoTabCompleter());

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
    public static MythicMobUtil getMythicMobUtil() {
        return MythicMobUtil.INSTANCE;
    }

}
