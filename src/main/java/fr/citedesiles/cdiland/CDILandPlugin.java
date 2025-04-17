package fr.citedesiles.cdiland;

import fr.citedesiles.cdiland.commands.AdminCommand;
import fr.citedesiles.cdiland.corruption.CorruptionManager;
import fr.citedesiles.cdiland.listener.*;
import fr.citedesiles.cdiland.mysql.CheckTable;
import fr.citedesiles.cdiland.mysql.DatabaseManager;
import fr.citedesiles.cdiland.mysql.TeamSyncSQL;
import fr.citedesiles.cdiland.npc.OnNPCInteract;
import fr.citedesiles.cdiland.objectif.ObjectifManager;
import fr.citedesiles.cdiland.objects.CDIPlayerManager;
import fr.citedesiles.cdiland.objects.CDITeamManager;
import fr.citedesiles.cdiland.runnable.CorruptionRunnable;
import fr.citedesiles.cdiland.runnable.SaveObjectifRunnable;
import fr.citedesiles.cdiland.runnable.ScoreboardRunnable;
import fr.citedesiles.cdiland.runnable.TeamSyncRunnable;
import fr.citedesiles.cdiland.utils.ConfigManager;
import fr.citedesiles.cdiland.utils.ScoreboardTeamManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class CDILandPlugin extends JavaPlugin {
    private static CDILandPlugin instance;
    private ConfigManager configManager;
    private CDITeamManager teamManager;
    private CDIPlayerManager playerManager;
    private CorruptionManager corruptionManager;
    private ObjectifManager objectifManager;

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager(this);
        teamManager = new CDITeamManager(this);
        playerManager = new CDIPlayerManager(this);
        corruptionManager = new CorruptionManager();

        getLogger().info("CDILand plugin enabled");

        try {
            configManager.loadConfig();
        } catch (IOException e) {
            getLogger().severe("An error occurred while loading config.yml");
        }
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");


        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new OnLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new OnJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new OnDeathListener(), this);
        getServer().getPluginManager().registerEvents(new OnArrowShootedByAnotherPlayerListener(), this);
        getServer().getPluginManager().registerEvents(new OnBlueAxolotKilledListener(), this);
        getServer().getPluginManager().registerEvents(new OnDirtMinedListener(), this);
        getServer().getPluginManager().registerEvents(new OnDragonKilledListener(), this);
        getServer().getPluginManager().registerEvents(new OnEnderpearlShootListener(), this);
        getServer().getPluginManager().registerEvents(new OnHoneyCraftListener(), this);
        getServer().getPluginManager().registerEvents(new OnKillListener(), this);
        getServer().getPluginManager().registerEvents(new OnNetheriteHoeCraftListener(), this);
        getServer().getPluginManager().registerEvents(new OnToolBrokeListener(), this);
        getServer().getPluginManager().registerEvents(new OnTotemUsedListener(), this);
        getServer().getPluginManager().registerEvents(new OnWardenKilledListener(), this);
        getServer().getPluginManager().registerEvents(new OnNPCInteract(), this);
        getServer().getPluginManager().registerEvents(new OnClickInventoryListener(), this);

        DatabaseManager.initAllDataBaseConnections();
        CheckTable.checkTables();
        TeamSyncSQL.getAllTeamsFromDB(this);
        ScoreboardTeamManager scoreboardTeamManager = new ScoreboardTeamManager(this);
        scoreboardTeamManager.initAllTeams();

        TeamSyncRunnable teamSyncRunnable = new TeamSyncRunnable();
        teamSyncRunnable.runTaskTimerAsynchronously(this, 0, 20 * 5);

        SaveObjectifRunnable saveObjectifRunnable = new SaveObjectifRunnable();
        saveObjectifRunnable.runTaskTimerAsynchronously(this, 0, 20 * 20);

        ScoreboardRunnable scoreboardRunnable = new ScoreboardRunnable(this);
        scoreboardRunnable.runTaskTimer(this, 0, 20 * 5);

        CorruptionRunnable corruptionRunnable = new CorruptionRunnable();
        corruptionRunnable.runTaskTimer(this, 0, 0);

        getCommand("admin").setExecutor(new AdminCommand());
        objectifManager = new ObjectifManager();
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

    public CDIPlayerManager playerManager() {
        return playerManager;
    }

    public CorruptionManager corruptionManager() {
        return corruptionManager;
    }

    public ObjectifManager objectifManager() {
        return objectifManager;
    }
}
