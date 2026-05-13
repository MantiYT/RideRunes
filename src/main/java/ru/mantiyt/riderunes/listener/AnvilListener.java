package ru.mantiyt.riderunes.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.mantiyt.riderunes.RideRunes;
import ru.mantiyt.riderunes.util.ColorUtil;

import java.util.ArrayList;
import java.util.List;

public class AnvilListener implements Listener {

    private final NamespacedKey immortalityKey = new NamespacedKey(RideRunes.getInstance(), "bessmertie");
    private final NamespacedKey recoveryKey = new NamespacedKey(RideRunes.getInstance(), "vosstanovlenie");

    @EventHandler
    public void onAnvilPrepare(PrepareAnvilEvent event) {
        ItemStack totem = event.getInventory().getItem(0);
        ItemStack rune = event.getInventory().getItem(1);

        if (totem == null || rune == null) return;
        if (totem.getType() != Material.TOTEM_OF_UNDYING) return;

        ItemMeta runeMeta = rune.getItemMeta();
        if (runeMeta == null) return;

        boolean hasImmortality = runeMeta.getPersistentDataContainer().has(immortalityKey, PersistentDataType.INTEGER);
        boolean hasRecovery = runeMeta.getPersistentDataContainer().has(recoveryKey, PersistentDataType.INTEGER);

        if (!hasImmortality && !hasRecovery) return;

        ItemMeta totemMeta = totem.getItemMeta();
        if (totemMeta == null) return;

        boolean alreadyHasImmortality = totemMeta.getPersistentDataContainer().has(immortalityKey, PersistentDataType.INTEGER);
        boolean alreadyHasRecovery = totemMeta.getPersistentDataContainer().has(recoveryKey, PersistentDataType.INTEGER);

        if ((hasImmortality && alreadyHasImmortality) || (hasRecovery && alreadyHasRecovery)) return;

        if (rune.getAmount() == 1) {
            ItemStack result = totem.clone();
            ItemMeta meta = result.getItemMeta();

            NamespacedKey keyToAdd = hasImmortality ? immortalityKey : recoveryKey;
            meta.getPersistentDataContainer().set(keyToAdd, PersistentDataType.INTEGER, 1);

            List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();

            boolean hasSection = false;
            for (String line : lore) {
                if (line.contains(ColorUtil.colorize("&x&4&0&8&B&F&BЭффекты при активации:"))) {
                    hasSection = true;
                    break;
                }
            }

            if (!hasSection) {
                lore.add(ColorUtil.colorize(""));
                lore.add(ColorUtil.colorize("&x&4&0&8&B&F&BЭффекты при активации:"));
            }

            if (hasImmortality) {
                lore.add(ColorUtil.colorize(" &6• Бессмертие"));
            } else if (hasRecovery) {
                lore.add(ColorUtil.colorize(" &x&F&F&0&0&5&3• Восстановление"));
            }

            lore.add(ColorUtil.colorize(" "));
            lore.add(ColorUtil.colorize("&7Применятся после использования"));

            meta.setLore(lore);
            result.setItemMeta(meta);
            event.getInventory().setRepairCost(10);
            event.setResult(result);
        }
    }
}