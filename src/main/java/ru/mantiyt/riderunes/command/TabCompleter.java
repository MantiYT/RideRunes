package ru.mantiyt.riderunes.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("riderunes.admin")) return new ArrayList<>();

        if (args.length == 1) {
            return null;
        } else if (args.length == 2) {
            return Arrays.asList("immortality", "recovery");
        } else if (args.length == 3) {
            return Arrays.asList("1", "5", "10", "32", "64");
        }
        return new ArrayList<>();
    }
}