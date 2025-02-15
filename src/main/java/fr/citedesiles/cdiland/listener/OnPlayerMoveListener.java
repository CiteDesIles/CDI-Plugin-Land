package fr.citedesiles.cdiland.listener;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objectif.CDIObjectif;
import fr.citedesiles.cdiland.objects.CDIPlayer;
import fr.citedesiles.cdiland.objects.CDITeam;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMoveListener implements Listener {

    @EventHandler
    public void on(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        CDIPlayer cdiPlayer = CDILandPlugin.instance().playerManager().get(player);
        if(cdiPlayer == null) {
            return;
        }
        CDITeam cdiTeam = CDILandPlugin.instance().teamManager().getTeam(cdiPlayer.getTeam());
        if(cdiTeam == null) {
            return;
        }

        CDIObjectif objectifAngel = CDILandPlugin.instance().objectifManager().get(cdiTeam, "angel");
        if(objectifAngel == null) {
            return;
        }
        if(objectifAngel.getValue() < player.getLocation().getY()) {
            objectifAngel.setValue((int) player.getLocation().getY());
        }
        int _y = (int) (player.getLocation().getY() * -1);

        CDIObjectif objectifDig = CDILandPlugin.instance().objectifManager().get(cdiTeam, "dig");
        if(objectifDig == null) {
            return;
        }
        if(objectifDig.getValue() < _y) {
            objectifDig.setValue(_y);
        }


    }
}
