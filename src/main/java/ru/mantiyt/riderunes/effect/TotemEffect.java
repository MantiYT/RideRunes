package ru.mantiyt.riderunes.effect;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import ru.mantiyt.riderunes.RideRunes;

public class TotemEffect {

    public static void applyImmortality(Player player) {
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

    public static void applyRecovery(Player player) {
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
}