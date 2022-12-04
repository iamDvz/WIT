package ru.iamdvz.wit;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.jetbrains.annotations.NotNull;
import ru.iamdvz.wit.utils.MythicMobs;
import ru.iamdvz.wit.utils.Utils;

import java.util.Objects;

public class WITPlaceholders extends PlaceholderExpansion {
    private final WIT plugin;
    private static String stringFormat = WIT.getInstance().getConfig().getString("format-of-mythic-mobs-string");
    private static boolean showLvl = WIT.getInstance().getConfig().getBoolean("show-level-of-mythic-mobs");
    private static boolean showHealth = WIT.getInstance().getConfig().getBoolean("show-health-of-mythic-mobs");
    private static String emptyChar = WIT.getInstance().getConfig().getString("mythic-mob-health-bar-empty");
    private static String fullChar = WIT.getInstance().getConfig().getString("mythic-mob-health-bar-full");
    private static String nothingString = WIT.getInstance().getConfig().getString("nothing-string");

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

        if (identifier.equals("output") && p.isOnline()) {
            RayTraceResult result = Utils.getRayTraceResult((Player) p);
            if (result == null)
                return nothingString;

            if (MythicMobs.isMythicMob(result.getHitEntity())) {
                String s = stringFormat;
                s = s.replace("{name}", Objects.requireNonNull(MythicMobs.getMythicMobDisplayName(result.getHitEntity())));
                if (showLvl) s = s.replace("{level}", String.valueOf(MythicMobs.getMythicMobLevel(result.getHitEntity())));
                if (showHealth) s = s.replace("{health}", String.valueOf(MythicMobs.getMythicMobCurrentHealth(result.getHitEntity())));
                return s.replace("{level}", "").replace("{health}", "");
            }
        }

        if (identifier.split(":")[0].equals("mobHealth") && p.isOnline()) {
            RayTraceResult result = Utils.getRayTraceResult((Player) p);
            if (result == null)
                return nothingString;
            if (MythicMobs.isMythicMob(result.getHitEntity())) {
                String s;
                int size = Integer.parseInt(identifier.split(":")[1]);
                return Utils.makeBar(emptyChar, fullChar, Integer.parseInt(identifier.split(":")[1]),
                        MythicMobs.getMythicMobMaxHealth(result.getHitEntity()), MythicMobs.getMythicMobCurrentHealth(result.getHitEntity()));
            }
        }

        if (!Utils.getRayTraceResult((Player) p).getHitBlock().getType().equals(Material.AIR))
            return nothingString;

        return "ERR";
    }
}
