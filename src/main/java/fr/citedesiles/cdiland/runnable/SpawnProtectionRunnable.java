package fr.citedesiles.cdiland.runnable;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class SpawnProtectionRunnable extends BukkitRunnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.isOp()) continue;
            if (player.getGameMode() != GameMode.SURVIVAL && player.getGameMode() != GameMode.ADVENTURE) continue;

            if (player.getLocation().getWorld().getName().equals("world") && player.getLocation().distance(new Location(Bukkit.getWorld("world"), 0, 0, 0)) < 100) {
                player.setGameMode(GameMode.ADVENTURE);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aVous êtes dans le spawn !"));
            } else {
                player.setGameMode(GameMode.SURVIVAL);
            }
        }
    }
}
