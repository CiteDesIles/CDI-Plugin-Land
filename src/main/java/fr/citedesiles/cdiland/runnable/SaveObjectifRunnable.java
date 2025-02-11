package fr.citedesiles.cdiland.runnable;

import fr.citedesiles.cdiland.mysql.SyncObjectifSQL;
import org.bukkit.scheduler.BukkitRunnable;

public class SaveObjectifRunnable extends BukkitRunnable {
    @Override
    public void run() {
        SyncObjectifSQL.syncToDB();
    }
}
