package fr.citedesiles.cdiland.objects;

import fr.citedesiles.cdiland.CDILandPlugin;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CDIPlayerManager {
    private List<CDIPlayer> players = new ArrayList<>();
    private CDILandPlugin plugin;

    public CDIPlayerManager(CDILandPlugin plugin) {
        this.plugin = plugin;
    }

    public void add(CDIPlayer player) {
        players.add(player);
    }

    public CDIPlayer get(Player player) {
        for (CDIPlayer cdiPlayer : players) {
            if (cdiPlayer.getUuid().equals(player.getUniqueId())) {
                return cdiPlayer;
            }
        }
        return null;
    }

    public CDIPlayer get(UUID uuid) {
        for (CDIPlayer cdiPlayer : players) {
            if (cdiPlayer.getUuid().equals(uuid)) {
                return cdiPlayer;
            }
        }
        return null;
    }

    public void remove(CDIPlayer player) {
        players.remove(player);
    }

    public void remove(UUID uuid) {
        players.removeIf(cdiPlayer -> cdiPlayer.getUuid().equals(uuid));
    }

    public void remove(Player player) {
        players.removeIf(cdiPlayer -> cdiPlayer.getUuid().equals(player.getUniqueId()));
    }

    public List<CDIPlayer> getPlayers() {
        return players;
    }
}
