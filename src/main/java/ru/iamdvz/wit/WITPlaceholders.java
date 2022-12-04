package ru.iamdvz.wit;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.jetbrains.annotations.NotNull;
import ru.iamdvz.wit.utils.MythicMobs;
import ru.iamdvz.wit.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WITPlaceholders extends PlaceholderExpansion {
    private final WIT plugin;
    private static String stringFormat = WIT.getInstance().getConfig().getString("format-of-mythic-mobs-string");
    private static boolean showLvl = WIT.getInstance().getConfig().getBoolean("show-level-of-mythic-mobs");
    private static boolean showHealth = WIT.getInstance().getConfig().getBoolean("show-health-of-mythic-mobs");
    private static String emptyChar = WIT.getInstance().getConfig().getString("mythic-mob-health-bar-empty");
    private static String fullChar = WIT.getInstance().getConfig().getString("mythic-mob-health-bar-full");
    private static String nothingString = WIT.getInstance().getConfig().getString("nothing-string");
    private static ConfigurationSection mobAndItString = WIT.getInstance().getConfig().getConfigurationSection("mob-and-it-parameters");

    public WITPlaceholders(WIT plugin) {
        this.plugin = plugin;
        if (stringFormat == null) stringFormat = "{name} {level} {health}";
    }
    public static void WITPlaceholdersReload() {
        stringFormat = WIT.getInstance().getConfig().getString("format-of-mythic-mobs-string");
        showLvl = WIT.getInstance().getConfig().getBoolean("show-level-of-mythic-mobs");
        showHealth = WIT.getInstance().getConfig().getBoolean("show-health-of-mythic-mobs");
        emptyChar = WIT.getInstance().getConfig().getString("mythic-mob-health-bar-empty");
        fullChar = WIT.getInstance().getConfig().getString("mythic-mob-health-bar-full");
        nothingString = WIT.getInstance().getConfig().getString("nothing-string");
        mobAndItString = WIT.getInstance().getConfig().getConfigurationSection("mob-and-it-parameters");
    }

    @NotNull
    @Override
    public String getAuthor() {
        return "iamDvz";
    }

    @NotNull
    @Override
    public String getIdentifier() {
        return "WIT";
    }

    @NotNull
    @Override
    public String getVersion() {
        return "1.0";
    }


    public String onRequest(OfflinePlayer p, @NotNull String identifier) {
        if (!((Player) p).hasPermission("wit.use"))
            return "NoPerms";
        RayTraceResult result = Utils.getRayTraceResult((Player) p);
        if (result == null || (result.getHitBlock() != null && !result.getHitBlock().getType().equals(Material.AIR)))
            return nothingString;

        Entity resultEntity = Utils.getRayTraceResult((Player) p).getHitEntity();

        if (identifier.equals("output") && p.isOnline()) {
            if (MythicMobs.isMythicMob(resultEntity)) {
                String s = stringFormat;
                s = s.replace("{name}", Objects.requireNonNull(MythicMobs.getMythicMobDisplayName(resultEntity)));
                if (showLvl) s = s.replace("{level}", String.valueOf(MythicMobs.getMythicMobLevel(resultEntity)));
                if (showHealth) s = s.replace("{health}", String.valueOf(MythicMobs.getMythicMobCurrentHealth(resultEntity)));
                return s.replace("{level}", "").replace("{health}", "");
            }
        }

        if (identifier.split(":")[0].equals("mobHealth") && p.isOnline()) {
            if (MythicMobs.isMythicMob(resultEntity)) {
                return Utils.makeBar(emptyChar, fullChar, Integer.parseInt(identifier.split(":")[1]),
                        MythicMobs.getMythicMobMaxHealth(resultEntity), MythicMobs.getMythicMobCurrentHealth(resultEntity));
            }
        }

        if (identifier.split(":")[0].equals("mobGetParameter") && p.isOnline()) {
            if (MythicMobs.isMythicMob(resultEntity)) {
                if (mobAndItString.contains(MythicMobs.getMythicMobConfigName(resultEntity))) {
                    return mobAndItString.getString(MythicMobs.getMythicMobConfigName(resultEntity)+"."+(identifier.split(":")[1]));
                }
            }
            return MythicMobs.getMythicMobConfigName(resultEntity);
        }

        return "ERR";
    }
}
