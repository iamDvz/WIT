package ru.iamdvz.wit;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.jetbrains.annotations.NotNull;
import ru.iamdvz.wit.functions.WITFunc;
import ru.iamdvz.wit.utils.Utils;


public class WITPlaceholders extends PlaceholderExpansion {
    public WITPlaceholders(WIT plugin) {}

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
            return WITFunc.INSTANCE.getNothingString();

        if (identifier.equals("output")) {
            return WITFunc.outputFunction(result.getHitEntity());
        }

        if (identifier.split(":")[0].equals("mobHealth")) {
            return WITFunc.healthBarFunc(result.getHitEntity(), Integer.parseInt(identifier.split(":")[1]));
        }

        if (identifier.split(":")[0].equals("mobGetParameter")) {
            return WITFunc.getParameter(result.getHitEntity(), identifier.split(":")[1]);
        }

        return "ERR";
    }
}
