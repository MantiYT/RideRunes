package ru.mantiyt.riderunes.listener;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import ru.mantiyt.riderunes.RideRunes;

public class TotemUseListener implements Listener {

    private final NamespacedKey immortalityKey = new NamespacedKey(RideRunes.getInstance(), "bessmertie");
    private final NamespacedKey recoveryKey = new NamespacedKey(RideRunes.getInstance(), "vosstanovlenie");

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTotemActivate(EntityResurrectEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        ItemStack totem = getUsedTotem(player);

        if (totem == null) return;

        ItemMeta meta = totem.getItemMeta();
        if (meta == null) return;

        boolean hasRecovery = meta.getPersistentDataContainer().has(recoveryKey, PersistentDataType.INTEGER);
        boolean hasImmortality = meta.getPersistentDataContainer().has(immortalityKey, PersistentDataType.INTEGER);

        if (hasRecovery) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (player.isOnline() && !player.isDead()) {
                        player.setHealth(player.getMaxHealth());
                        player.setFoodLevel(20);
                        player.setSaturation(20f);

                        player.removePotionEffect(PotionEffectType.POISON);
                        player.removePotionEffect(PotionEffectType.WITHER);
                        player.removePotionEffect(PotionEffectType.HUNGER);
                        player.removePotionEffect(PotionEffectType.WEAKNESS);
                    }
                }
            }.runTaskLater(RideRunes.getInstance(), 1L);
        }

        if (hasImmortality) {
            player.setInvulnerable(true);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (player.isOnline()) {
                        player.setInvulnerable(false);
                    }
                }
            }.runTaskLater(RideRunes.getInstance(), 60L);
        }
    }

    private ItemStack getUsedTotem(Player player) {
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        ItemStack offHand = player.getInventory().getItemInOffHand();

        if (mainHand.getType() == org.bukkit.Material.TOTEM_OF_UNDYING) {
            return mainHand;
        } else if (offHand.getType() == org.bukkit.Material.TOTEM_OF_UNDYING) {
            return offHand;
        }
        return null;
    }
}