package fr.citedesiles.cdiland.runnable;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objects.CDITeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardRunnable extends BukkitRunnable {

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            final ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
            final Objective objective = scoreboard.registerNewObjective("general", "dummy", "§6§lCité des îles");
            objective.setDisplaySlot(org.bukkit.scoreboard.DisplaySlot.SIDEBAR);
            final Score space = objective.getScore("§7");
            space.setScore(15);

            final Score urTeam = objective.getScore("§fVotre équipe :");
            urTeam.setScore(14);
            CDITeam cdiTeam = CDILandPlugin.instance().teamManager().getTeam(CDILandPlugin.instance().playerManager().get(player).getTeam());
            final Score urTeamName = objective.getScore("§e" + cdiTeam.getDisplayName());
            urTeamName.setScore(13);

            final Score space2 = objective.getScore("§8");
            space2.setScore(12);

            final Score urPoints = objective.getScore("§fStats :");
            urPoints.setScore(11);
            final Score urPointsValue = objective.getScore("§e§l" + cdiTeam.getMoney() + " G");
            urPointsValue.setScore(10);

            final Score urSP = objective.getScore("§b§l" + cdiTeam.getSupportPoints() + " SP");
            urSP.setScore(9);

            final Score space3 = objective.getScore("§9");
            space3.setScore(8);

            final Score urPlayers = objective.getScore("§fPosition: §c" + CDILandPlugin.instance().teamManager().position(cdiTeam));
            urPlayers.setScore(7);



            player.setScoreboard(scoreboard);

        }
    }
}
