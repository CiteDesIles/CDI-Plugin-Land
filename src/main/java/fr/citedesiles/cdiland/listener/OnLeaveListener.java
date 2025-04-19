package fr.citedesiles.cdiland.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnLeaveListener implements Listener {
    @EventHandler
    public void on(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.SPECTATOR)
            event.setQuitMessage(null);
        else
            event.setQuitMessage("§7(§c-§7) " + player.getName());
    }
}
