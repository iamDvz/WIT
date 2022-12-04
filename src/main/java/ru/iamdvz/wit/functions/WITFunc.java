package ru.iamdvz.wit.functions;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import ru.iamdvz.wit.utils.MythicMobs;
import ru.iamdvz.wit.utils.Utils;

import java.util.Objects;

public class WITFunc {

    private static String stringFormat = ru.iamdvz.wit.WIT.getInstance().getConfig().getString("format-of-mythic-mobs-string");
    private static boolean showLvl = ru.iamdvz.wit.WIT.getInstance().getConfig().getBoolean("show-level-of-mythic-mobs");
    private static boolean showHealth = ru.iamdvz.wit.WIT.getInstance().getConfig().getBoolean("show-health-of-mythic-mobs");
    private static String emptyChar = ru.iamdvz.wit.WIT.getInstance().getConfig().getString("mythic-mob-health-bar-empty");
    private static String fullChar = ru.iamdvz.wit.WIT.getInstance().getConfig().getString("mythic-mob-health-bar-full");
    public static String nothingString = ru.iamdvz.wit.WIT.getInstance().getConfig().getString("nothing-string");
    private static ConfigurationSection mobAndItString = ru.iamdvz.wit.WIT.getInstance().getConfig().getConfigurationSection("mob-and-it-parameters");
    public static double checkerDistance = ru.iamdvz.wit.WIT.getInstance().getConfig().getDouble("checker-distance");

    public static void WITPlaceholdersReload() {
        stringFormat = ru.iamdvz.wit.WIT.getInstance().getConfig().getString("format-of-mythic-mobs-string");
        showLvl = ru.iamdvz.wit.WIT.getInstance().getConfig().getBoolean("show-level-of-mythic-mobs");
        showHealth = ru.iamdvz.wit.WIT.getInstance().getConfig().getBoolean("show-health-of-mythic-mobs");
        emptyChar = ru.iamdvz.wit.WIT.getInstance().getConfig().getString("mythic-mob-health-bar-empty");
        fullChar = ru.iamdvz.wit.WIT.getInstance().getConfig().getString("mythic-mob-health-bar-full");
        nothingString = ru.iamdvz.wit.WIT.getInstance().getConfig().getString("nothing-string");
        mobAndItString = ru.iamdvz.wit.WIT.getInstance().getConfig().getConfigurationSection("mob-and-it-parameters");
        checkerDistance = ru.iamdvz.wit.WIT.getInstance().getConfig().getDouble("checker-distance");
    }

    public static String outputFunction(Entity entity) {
        if (!MythicMobs.isMythicMob(entity)) {return nothingString;}
        String s = stringFormat;
        s = s.replace("{name}", Objects.requireNonNull(MythicMobs.getMythicMobDisplayName(entity)));
        if (showLvl) s = s.replace("{level}", String.valueOf(MythicMobs.getMythicMobLevel(entity)));
        if (showHealth) s = s.replace("{health}", String.valueOf(MythicMobs.getMythicMobCurrentHealth(entity)));
        return s.replace("{level}", "").replace("{health}", "");
    }

    public static String healthBarFunc(Entity entity, int size) {
        if (!MythicMobs.isMythicMob(entity)) {return nothingString;}
        return Utils.makeBar(emptyChar, fullChar, size,
                MythicMobs.getMythicMobMaxHealth(entity), MythicMobs.getMythicMobCurrentHealth(entity));
    }
    public static String getParameter(Entity entity, String parameter) {
        if (!MythicMobs.isMythicMob(entity)) {return nothingString;}
        if (mobAndItString.contains(MythicMobs.getMythicMobConfigName(entity))) {
            return mobAndItString.getString(MythicMobs.getMythicMobConfigName(entity) + "." + parameter);
        }
        return nothingString;
    }
}
