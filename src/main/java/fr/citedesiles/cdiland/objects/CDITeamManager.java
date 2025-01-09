package fr.citedesiles.cdiland.objects;

import fr.citedesiles.cdiland.CDILandPlugin;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class CDITeamManager {
    private List<CDITeam> teams = new ArrayList<>();
    private CDILandPlugin plugin;

    public CDITeamManager(CDILandPlugin plugin) {
        this.plugin = plugin;
    }

    public void add(CDITeam team) {
        teams.add(team);
    }

    public List<CDITeam> getTeams() {
        return new ArrayList<>(teams);
    }

    public void addGoldToTeam(String teamName, long amount) {
        for (CDITeam team : teams) {
            if (team.getName().equals(teamName)) {
                team.setMoney(team.getMoney() + amount);
            }
        }
    }

    public CDITeam getTeam(String teamName) {
        for (CDITeam cdiTeam : teams) {
            if (cdiTeam.getName().equals(teamName)) {
                return cdiTeam;
            }
        }
        return null;
    }

    public int position(CDITeam team) {
        int position = 1;
        for (CDITeam cdiTeam : teams) {
            if (cdiTeam.getMoney() > team.getMoney() && !cdiTeam.getName().equals("adminTeam") && !cdiTeam.getName().equals("modTeam")) {
                position++;
            }
        }
        if(team.getName().equals("adminTeam") || team.getName().equals("modTeam")) {
            return 0;
        }
        return position;
    }
}
