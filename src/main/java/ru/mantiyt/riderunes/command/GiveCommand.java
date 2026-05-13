package ru.mantiyt.riderunes.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.mantiyt.riderunes.item.RuneItem;
import ru.mantiyt.riderunes.item.RuneType;
import ru.mantiyt.riderunes.util.ColorUtil;

public class GiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("riderunes.admin")) {
            sender.sendMessage(ColorUtil.colorize("&x&F&F&0&0&0&0▶ &fУ вас нет прав!"));
            return true;
        }
        if (args.length < 3) {
            sender.sendMessage(ColorUtil.colorize("&x&F&F&0&0&0&0▶ &fИспользование: /riderunes give <игрок> <руна> <количество>"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ColorUtil.colorize("&x&F&F&0&0&0&0▶ &fИгрок не найден!"));
            return true;
        }

        try {
            RuneType type = RuneType.fromString(args[1]);
            int amount = Integer.parseInt(args[2]);

            if (amount < 1 || amount > 64) {
                sender.sendMessage(ColorUtil.colorize("&x&F&F&0&0&0&0▶ &fКоличество должно быть от 1 до 64!"));
                return true;
            }

            target.getInventory().addItem(RuneItem.create(type, amount));
            sender.sendMessage(ColorUtil.colorize("&x&0&3&F&F&0&0▶ &fВыдали " + amount + "x " + type.getDisplayName() + " игроку " + target.getName()));
        } catch (IllegalArgumentException e) {
            sender.sendMessage(ColorUtil.colorize("&x&F&F&0&0&0&0▶ &fДоступные руны: immortality, recovery"));
        }
        return true;
    }
}