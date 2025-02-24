package fr.citedesiles.cdiland.listener;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objectif.CDIObjectif;
import fr.citedesiles.cdiland.objects.CDIPlayer;
import fr.citedesiles.cdiland.objects.CDITeam;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnDragonKilledListener implements Listener {
    @EventHandler
    public void on(EntityDeathEvent event) {
        if(event.getEntity().getType().equals(EntityType.ENDER_DRAGON)) {
            if(event.getEntity().getKiller() != null) {
                Player player = event.getEntity().getKiller();
                CDIPlayer cdiPlayer = CDILandPlugin.instance().playerManager().get(player);
                if(cdiPlayer == null) {
                    return;
                }
                CDITeam cdiTeam = CDILandPlugin.instance().teamManager().getTeam(cdiPlayer.getTeam());
                if(cdiTeam == null) {
                    return;
                }
                CDIObjectif objectif = CDILandPlugin.instance().objectifManager().get(cdiTeam, "dragon_slayer");
                if(objectif != null) {
                    objectif.setValue(objectif.getValue() + 1);
                }
            }
        }
    }
}
