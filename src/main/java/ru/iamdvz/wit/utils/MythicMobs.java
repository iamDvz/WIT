package ru.iamdvz.wit.utils;
import io.lumine.mythic.bukkit.BukkitAPIHelper;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.entity.Entity;

public class MythicMobs {
    public MythicMobs() {}

    private static BukkitAPIHelper api = MythicBukkit.inst().getAPIHelper();

    public static boolean isMythicMob(Entity entity) {
        if (entity != null) {
            return api.isMythicMob(entity);
        }
        return false;
    }

    public static String getMythicMobDisplayName(Entity entity) {
        ActiveMob activeMob = api.getMythicMobInstance(entity);
        return activeMob != null ? activeMob.getDisplayName() : null;
    }
    public static double getMythicMobLevel(Entity entity) {
        ActiveMob activeMob = api.getMythicMobInstance(entity);
        return activeMob != null ? activeMob.getLevel() : null;
    }

    public static double getMythicMobCurrentHealth(Entity entity) {
        ActiveMob activeMob = api.getMythicMobInstance(entity);
        return activeMob.getEntity().getHealth();
    }
    public static double getMythicMobMaxHealth(Entity entity) {
        ActiveMob activeMob = api.getMythicMobInstance(entity);
        return activeMob.getEntity().getMaxHealth();
    }
}
