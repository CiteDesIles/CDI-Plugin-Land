package fr.citedesiles.cdiland.npc;

import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnNPCInteract implements Listener {
    @EventHandler
    public void on(NpcInteractEvent event) {
//        if(!event.getInteractionType().equals(ActionTrigger.RIGHT_CLICK)) {
//            return;
//        }
//        Bukkit.broadcastMessage("NPC : " + event.getNpc().getData().getName());
        switch (event.getNpc().getData().getName()) {
            case "cdi-change-server" -> {
                ChangeServeurMenu changeServerMenu = new ChangeServeurMenu();
                changeServerMenu.open(event.getPlayer());
            }
        }
    }
}
