package fr.citedesiles.cdiland;

import org.bukkit.plugin.java.JavaPlugin;

public class CDILandPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("CDILand plugin enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("CDILand plugin disabled");
    }
}
