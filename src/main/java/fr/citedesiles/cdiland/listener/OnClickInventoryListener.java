package fr.citedesiles.cdiland.listener;

import fr.citedesiles.cdiland.utils.JoinFunctions;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnClickInventoryListener implements Listener {
    @EventHandler
    public void on(InventoryClickEvent event) {

        if (event.getView().getTitle().startsWith("§c§lChanger de serveur")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) {
                return;
            }
            if (event.getCurrentItem().getType().equals(Material.RED_CONCRETE)) {
                event.getWhoClicked().sendMessage("§fVous allez être redirigé vers le serveur §c§lCité");
                JoinFunctions.connect("cite", (Player) event.getWhoClicked());
                event.getWhoClicked().closeInventory();
                return;
            }
        }
    }
}
