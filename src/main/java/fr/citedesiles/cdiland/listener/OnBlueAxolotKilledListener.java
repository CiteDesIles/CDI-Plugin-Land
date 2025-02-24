package fr.citedesiles.cdiland.listener;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objectif.CDIObjectif;
import fr.citedesiles.cdiland.objects.CDIPlayer;
import fr.citedesiles.cdiland.objects.CDITeam;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnBlueAxolotKilledListener implements Listener {
    @EventHandler
    public void on(EntityDeathEvent event) {
        if(event.getEntity() instanceof Axolotl axolotl) {
            if(axolotl.getVariant().equals(Axolotl.Variant.BLUE)) {
                if(event.getEntity().getKiller() != null) {
                    if(event.getEntity().getKiller() instanceof Player player) {
                        CDIPlayer cdiPlayer = CDILandPlugin.instance().playerManager().get(player);
                        if(cdiPlayer == null) {
                            return;
                        }
                        CDITeam cdiTeam = CDILandPlugin.instance().teamManager().getTeam(cdiPlayer.getTeam());
                        if(cdiTeam == null) {
                            return;
                        }
                        CDIObjectif objectif = CDILandPlugin.instance().objectifManager().get(cdiTeam, "axolotl_nlue_killed");
                        if(objectif != null) {
                            objectif.setValue(objectif.getValue() + 1);
                        }
                    }
                }
            }
        }
    }
}
