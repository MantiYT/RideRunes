package ru.mantiyt.riderunes.util;

import org.bukkit.ChatColor;

public class ColorUtil {

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String stripColor(String message) {
        return ChatColor.stripColor(message);
    }
}