// item/RuneItem.java
package ru.mantiyt.riderunes.item;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.mantiyt.riderunes.RideRunes;
import ru.mantiyt.riderunes.util.ColorUtil;

import java.util.ArrayList;
import java.util.List;

public class RuneItem {

    public static ItemStack create(RuneType type, int amount) {
        ItemStack item = new ItemStack(type.getMaterial(), amount);
        ItemMeta meta = item.getItemMeta();

        if (type == RuneType.IMMORTALITY) {
            meta.setDisplayName(ColorUtil.colorize("&x&A&2&6&9&0&0Р&x&A&E&6&F&0&0у&x&B&9&7&6&0&0н&x&C&5&7&C&0&0а &x&D&C&8&8&0&0«&x&E&8&8&F&0&0Б&x&F&3&9&5&0&0е&x&F&F&9&B&0&0с&x&F&3&9&5&0&0с&x&E&8&8&F&0&0м&x&D&C&8&8&0&0е&x&D&1&8&2&0&0р&x&C&5&7&C&0&0т&x&B&9&7&6&0&0и&x&A&E&6&F&0&0е&x&A&2&6&9&0&0»"));
        } else if (type == RuneType.RECOVERY) {
            meta.setDisplayName(ColorUtil.colorize("&x&F&F&1&0&4&A«Восстановление»"));
        }

        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        NamespacedKey key = new NamespacedKey(RideRunes.getInstance(), type.getNbtKey());
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.colorize("&8Эффект руны"));
        lore.add(ColorUtil.colorize(" "));
        lore.add(ColorUtil.colorize(" &7Особенности:"));

        if (type == RuneType.IMMORTALITY) {
            lore.add(ColorUtil.colorize(" &x&F&F&9&B&0&0 - после активации тотема с этим эффектом,"));
            lore.add(ColorUtil.colorize(" &0.&x&F&F&9&B&0&0   Вы получите неуязвимость к урону"));
            lore.add(ColorUtil.colorize(" &0.&x&F&F&9&B&0&0   продолжительностью 3 секунды;"));
            lore.add(ColorUtil.colorize(" &x&F&F&9&B&0&0 - возможность наложить данный эффект"));
            lore.add(ColorUtil.colorize(" &0.&x&F&F&9&B&0&0   на тотем через наковальню;"));
        } else if (type == RuneType.RECOVERY) {
            lore.add(ColorUtil.colorize(" &x&F&F&1&0&4&A - после активации тотема с этим эффектом,"));
            lore.add(ColorUtil.colorize(" &0.&x&F&F&1&0&4&A   У вас полностью восстановиться здоровье;"));
            lore.add(ColorUtil.colorize(" &x&F&F&1&0&4&A - возможность наложить данный эффект"));
            lore.add(ColorUtil.colorize(" &0.&x&F&F&1&0&4&A   на тотем через наковальню;"));
        }

        lore.add(ColorUtil.colorize(" "));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}