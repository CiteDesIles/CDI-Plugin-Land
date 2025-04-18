package fr.citedesiles.cdiland.listener;

import fr.citedesiles.cdiland.utils.GiveKitUtility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnPlayerRespawnListener implements Listener {
    @EventHandler
    public void on(PlayerRespawnEvent event) {
        event.getPlayer().sendMessage("Pouf réapparition!");
        GiveKitUtility.giveKit(event.getPlayer());
    }
}
