package fr.citedesiles.cdiland;

import fr.citedesiles.cdiland.utils.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CDILandPlugin extends JavaPlugin {
    private static CDILandPlugin instance;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager(this);
        getLogger().info("CDILand plugin enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("CDILand plugin disabled");
    }

    public static CDILandPlugin instance() {
        return instance;
    }

    public ConfigManager configManager() {
        return configManager;
    }
}
