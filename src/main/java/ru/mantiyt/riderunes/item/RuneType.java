package ru.mantiyt.riderunes.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import ru.mantiyt.riderunes.util.ColorUtil;

public enum RuneType {
    IMMORTALITY("immortality", "bessmertie",
            ColorUtil.colorize("&x&A&2&6&9&0&0Р&x&A&E&6&F&0&0у&x&B&9&7&6&0&0н&x&C&5&7&C&0&0а &x&D&C&8&8&0&0«&x&E&8&8&F&0&0Б&x&F&3&9&5&0&0е&x&F&F&9&B&0&0с&x&F&3&9&5&0&0с&x&E&8&8&F&0&0м&x&D&C&8&8&0&0е&x&D&1&8&2&0&0р&x&C&5&7&C&0&0т&x&B&9&7&6&0&0и&x&A&E&6&F&0&0е&x&A&2&6&9&0&0»"),
            Material.ORANGE_DYE, "bessmertie"),

    RECOVERY("recovery", "vosstanovlenie",
            ColorUtil.colorize("&x&F&F&1&0&4&A«Восстановление»"),
            Material.RED_DYE, "vosstanovlenie");

    private final String key;
    private final String alias;
    private final String displayName;
    private final Material material;
    private final String nbtKey;

    RuneType(String key, String alias, String displayName, Material material, String nbtKey) {
        this.key = key;
        this.alias = alias;
        this.displayName = displayName;
        this.material = material;
        this.nbtKey = nbtKey;
    }

    public static RuneType fromString(String str) {
        for (RuneType type : values()) {
            if (type.key.equalsIgnoreCase(str) || type.alias.equalsIgnoreCase(str)) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getKey() { return key; }
    public String getAlias() { return alias; }
    public String getDisplayName() { return displayName; }
    public Material getMaterial() { return material; }
    public String getNbtKey() { return nbtKey; }
}