package fr.citedesiles.cdiland.utils;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objects.CDITeam;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Team;

public class ScoreboardTeamManager {

    private final CDILandPlugin plugin;

    public ScoreboardTeamManager(CDILandPlugin plugin) {
        this.plugin = plugin;
    }

    public void removeAllTeams() {
        for (Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
            team.unregister();
        }
    }

    public void initAllTeams() {
        removeAllTeams();
        for (CDITeam cdiTeam : plugin.teamManager().getTeams()) {
            Team team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(cdiTeam.getName());
            team.setPrefix(cdiTeam.getColor());
            team.setDisplayName(cdiTeam.getDisplayName());
        }
    }
}
