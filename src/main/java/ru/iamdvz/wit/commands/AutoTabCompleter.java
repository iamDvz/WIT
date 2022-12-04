package ru.iamdvz.wit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AutoTabCompleter implements TabCompleter {
    public AutoTabCompleter() {
    }

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, String[] args) {
        if (!(sender instanceof Player player)) {
            return null;
        } else {
            List<String> list = new ArrayList<>();
            List<String> auto = new ArrayList<>();
            if (args.length == 1) {
                list.add("info");
                if (player.hasPermission("wit.admin")) {
                    list.add("reload");
                }
            }

            for (String s : list) {
                if (s.startsWith(args[args.length - 1])) {
                    auto.add(s);
                }
            }

            return auto.isEmpty() ? list : auto;
        }
    }
}
