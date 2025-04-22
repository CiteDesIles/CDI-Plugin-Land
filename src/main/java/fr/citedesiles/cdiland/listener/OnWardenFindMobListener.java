package fr.citedesiles.cdiland.listener;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.corruption.Corruption;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;

public class OnWardenFindMobListener implements Listener {
    @EventHandler
    public void on(EntityTargetEvent event) {
        if (event.getTarget() == null || event.getTarget() instanceof Player) return;
        Corruption corruption = CDILandPlugin.instance().corruptionManager().getCorruption();
        if (corruption != null && corruption.isInCorruption(event.getEntity().getLocation())) event.setCancelled(true);
    }

    @EventHandler
    public void on(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) return;
        Corruption corruption = CDILandPlugin.instance().corruptionManager().getCorruption();
        if (corruption != null && corruption.isInCorruption(event.getEntity().getLocation())) event.setCancelled(true);
    }
}
