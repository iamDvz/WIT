package ru.iamdvz.wit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.iamdvz.wit.WIT;
import ru.iamdvz.wit.WITPlaceholders;

public class WITCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String prefix = ChatColor.GREEN + "[WIT] " + ChatColor.WHITE;
        if (sender.hasPermission("wit.admin") && args[0].equalsIgnoreCase("reload")) {
            WIT.getInstance().reloadConfig();
            WITPlaceholders.WITPlaceholdersReload();
            sender.sendMessage(prefix + "Config reloaded");
            return true;
        }
        return false;
    }
}
