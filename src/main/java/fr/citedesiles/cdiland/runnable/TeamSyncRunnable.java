package fr.citedesiles.cdiland.runnable;

import fr.citedesiles.cdiland.mysql.TeamSyncSQL;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamSyncRunnable extends BukkitRunnable {
    @Override
    public void run() {
        TeamSyncSQL.updateAllTeamsFromDB();
    }
}
