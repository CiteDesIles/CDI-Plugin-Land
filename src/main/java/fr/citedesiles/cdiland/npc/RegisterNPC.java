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
        npcData.setSkin("https://textures.minecraft.net/texture/eabe5cf740441c51c0d9cd1822e33521fc5ab2878e6440eac0e1fc0fa21a0e9f");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
    }
}
