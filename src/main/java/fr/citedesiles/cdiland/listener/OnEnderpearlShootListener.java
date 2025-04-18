package fr.citedesiles.cdiland.listener;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objectif.CDIObjectif;
import fr.citedesiles.cdiland.objects.CDIPlayer;
import fr.citedesiles.cdiland.objects.CDITeam;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class OnEnderpearlShootListener implements Listener {
    @EventHandler
    public void on(PlayerFishEvent event) {
        if(event.getCaught() != null) {
            CDIPlayer cdiPlayer = CDILandPlugin.instance().playerManager().get(event.getPlayer());
            if(cdiPlayer == null) {
                return;
            }
            CDITeam cdiTeam = CDILandPlugin.instance().teamManager().getTeam(cdiPlayer.getTeam());
            if(cdiTeam == null) {
                return;
            }
            CDIObjectif objectif = CDILandPlugin.instance().objectifManager().get(cdiTeam, "ender_pearl");
            if(objectif != null) {
                objectif.setValue(objectif.getValue() + 1);
            }
        }
    }
}
