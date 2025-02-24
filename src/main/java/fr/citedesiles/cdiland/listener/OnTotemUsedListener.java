package fr.citedesiles.cdiland.listener;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objectif.CDIObjectif;
import fr.citedesiles.cdiland.objects.CDIPlayer;
import fr.citedesiles.cdiland.objects.CDITeam;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;

public class OnTotemUsedListener implements Listener {
    @EventHandler
    public void on(EntityResurrectEvent event) {
        if(!event.isCancelled()) {
            Player player = (Player) event.getEntity();
            CDIPlayer cdiPlayer = CDILandPlugin.instance().playerManager().get(player);
            if(cdiPlayer == null) {
                return;
            }
            CDITeam cdiTeam = CDILandPlugin.instance().teamManager().getTeam(cdiPlayer.getTeam());
            if(cdiTeam == null) {
                return;
            }
            CDIObjectif objectif = CDILandPlugin.instance().objectifManager().get(cdiTeam, "totem_of_undying");
            if(objectif != null) {
                objectif.setValue(objectif.getValue() + 1);
            }
        }
    }
}
