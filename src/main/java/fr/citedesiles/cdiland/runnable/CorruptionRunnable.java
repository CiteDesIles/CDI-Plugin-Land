package fr.citedesiles.cdiland.runnable;

import fr.citedesiles.cdiland.CDILandPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CorruptionRunnable extends BukkitRunnable {
    @Override
    public void run() {
        CDILandPlugin.instance().corruptionManager().tick();
    }
}
