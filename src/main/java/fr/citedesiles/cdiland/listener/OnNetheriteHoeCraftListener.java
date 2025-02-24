package fr.citedesiles.cdiland.listener;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objectif.CDIObjectif;
import fr.citedesiles.cdiland.objects.CDIPlayer;
import fr.citedesiles.cdiland.objects.CDITeam;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnNetheriteHoeCraftListener implements Listener {
    @EventHandler
    public void on(PlayerInteractEvent event) {
        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_HOE)) {
            if(
                event.getClickedBlock().getType().equals(Material.DIRT) ||
                event.getClickedBlock().getType().equals(Material.GRASS_BLOCK)
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
                CDIObjectif cdiObjectif = CDILandPlugin.instance().objectifManager().get(team, "netherite_houe");
                if(cdiObjectif == null) {
                    return;
                }
                cdiObjectif.setValue(cdiObjectif.getValue() + 1);
            }
        }
    }
}
