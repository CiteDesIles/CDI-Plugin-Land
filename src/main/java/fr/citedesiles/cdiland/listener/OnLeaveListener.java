package fr.citedesiles.cdiland.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnLeaveListener implements Listener {
    @EventHandler
    public void on(PlayerQuitEvent event) {
        event.setQuitMessage("§7(§c-§7) " + event.getPlayer().getName());
    }
}
