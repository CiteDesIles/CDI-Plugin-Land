package fr.citedesiles.cdiland;

import fr.citedesiles.cdiland.objects.CDITeamManager;
import fr.citedesiles.cdiland.utils.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CDILandPlugin extends JavaPlugin {
    private static CDILandPlugin instance;
    private ConfigManager configManager;
    private CDITeamManager teamManager;

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager(this);
        teamManager = new CDITeamManager(this);
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

    public CDITeamManager teamManager() {
        return teamManager;
    }
}
