package fr.citedesiles.cdiland.listener;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objectif.CDIObjectif;
import fr.citedesiles.cdiland.objects.CDIPlayer;
import fr.citedesiles.cdiland.objects.CDITeam;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnArrowShootedByAnotherPlayerListener implements Listener {
    @EventHandler
    public void on(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player player) {
            if(event.getDamager() instanceof Player damager) {
                if(event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
                    CDIPlayer cditarget = CDILandPlugin.instance().playerManager().get(player);
                    CDIPlayer cdidamager = CDILandPlugin.instance().playerManager().get(damager);
                    if(cditarget != null && cdidamager != null) {
                        if(!cditarget.getTeam().equals(cdidamager.getTeam())) {
                            CDITeam team = CDILandPlugin.instance().teamManager().getTeam(cdidamager.getTeam());
                            CDIObjectif objectif = CDILandPlugin.instance().objectifManager().get(team, "arrow_shoot_on_player");
                            if(objectif != null) {
                                objectif.setValue(objectif.getValue() + 1);
                            }
                        }
                    }
                }
            }
        }
    }
}
