package fr.citedesiles.cdiland.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.citedesiles.cdiland.CDILandPlugin;
import org.bukkit.entity.Player;

public class JoinFunctions {
    public static void connect(String serveurName, Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serveurName);
        player.sendPluginMessage(CDILandPlugin.instance(), "BungeeCord", out.toByteArray());
    }
}
