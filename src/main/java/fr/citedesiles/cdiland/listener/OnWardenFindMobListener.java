package fr.citedesiles.cdiland.listener;

import org.bukkit.entity.Player;
import org.bukkit.entity.Warden;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class OnWardenFindMobListener implements Listener {
    @EventHandler
    public void on(EntityTargetEvent event) {
        if(event.getEntity() instanceof Warden warden)  {
            if(!(event.getTarget() instanceof Player)) {
                event.setCancelled(true);
            }
        }
    }
}
