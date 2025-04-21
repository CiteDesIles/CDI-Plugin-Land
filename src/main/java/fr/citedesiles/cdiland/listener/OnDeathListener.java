package fr.citedesiles.cdiland.listener;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objectif.CDIObjectif;
import fr.citedesiles.cdiland.objects.CDIPlayer;
import fr.citedesiles.cdiland.objects.CDITeam;
import fr.citedesiles.cdiland.objects.CDITeamManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeathListener implements Listener {
    @EventHandler
    public void on(PlayerDeathEvent event) {
        Player player = event.getEntity();
        CDIPlayer cdiPlayer = CDILandPlugin.instance().playerManager().get(player);
        if(cdiPlayer == null) {
            return;
        }
        CDITeam team = CDILandPlugin.instance().teamManager().getTeam(cdiPlayer.getTeam());
        CDIObjectif cdiObjectif = CDILandPlugin.instance().objectifManager().get(team, "death");
        if(cdiObjectif == null) {
            return;
        }
        cdiObjectif.setValue(cdiObjectif.getValue() + 1);
    }
}
