package fr.citedesiles.cdiland.npc;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcData;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public class RegisterNPC {
    public static void createNPC(UUID uuid, String name) {
        Location location = new Location(Bukkit.getWorld("world"), 2.5, 68, -8.5, -180, 0);
        NpcData npcData = new NpcData("cdi-change-server", uuid, location);
        npcData.setDisplayName("§6Changer de serveur");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        //npcData.setSkin(skin.get());
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
    }
}
