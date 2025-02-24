package fr.citedesiles.cdiland.listener;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objectif.CDIObjectif;
import fr.citedesiles.cdiland.objects.CDIPlayer;
import fr.citedesiles.cdiland.objects.CDITeam;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnDirtMinedListener implements Listener {
    @EventHandler
    public void on(BlockBreakEvent event) {
        if(
            event.getBlock().getType().equals(Material.DIRT) ||
            event.getBlock().getType().equals(Material.GRASS_BLOCK) ||
            event.getBlock().getType().equals(Material.PODZOL) ||
            event.getBlock().getType().equals(Material.COARSE_DIRT) ||
            event.getBlock().getType().equals(Material.MYCELIUM) ||
            event.getBlock().getType().equals(Material.FARMLAND) ||
            event.getBlock().getType().equals(Material.DIRT_PATH) ||
            event.getBlock().getType().equals(Material.ROOTED_DIRT)
        ) {
            Player player = event.getPlayer();
            CDIPlayer cdiPlayer = CDILandPlugin.instance().playerManager().get(player);
            if(cdiPlayer == null) {
                return;
            }
            CDITeam team = CDILandPlugin.instance().teamManager().getTeam(cdiPlayer.getTeam());
            if(team == null) {
                return;
            }
            CDIObjectif cdiObjectif = CDILandPlugin.instance().objectifManager().get(team, "dirt");
            if(cdiObjectif == null) {
                return;
            }
            cdiObjectif.setValue(cdiObjectif.getValue() + 1);
        }
    }
}
