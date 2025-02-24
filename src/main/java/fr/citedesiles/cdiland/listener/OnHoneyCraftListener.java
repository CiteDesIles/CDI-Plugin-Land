package fr.citedesiles.cdiland.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class OnHoneyCraftListener implements Listener {
    @EventHandler
    public void on(CraftItemEvent event) {
        if(event.getRecipe().getResult().getType().equals(org.bukkit.Material.HONEY_BLOCK)) {
            event.setCancelled(true);
        }
    }
}
