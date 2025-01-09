package fr.citedesiles.cdiland.mysql;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objects.CDITeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class TeamSyncSQL {

    public static void getAllTeamsFromDB(CDILandPlugin plugin) {
        try {
            Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TEAM");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String tag = resultSet.getString("tag");
                String color = resultSet.getString("color");
                String team_leader = resultSet.getString("team_leader");
                String display_name = resultSet.getString("display_name");
                long money = resultSet.getLong("golds");
                int supportPoints = resultSet.getInt("supportPoints");
                int slots = resultSet.getInt("Slots");
                CDITeam cdiTeam = new CDITeam(name, display_name, UUID.fromString(team_leader), new ArrayList<UUID>(), money, supportPoints, slots, color, tag);
                plugin.teamManager().add(cdiTeam);
                Connection connectionPlayer = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
                PreparedStatement preparedStatementPlayer = connectionPlayer.prepareStatement("SELECT * FROM PLAYER WHERE team = ?");
                preparedStatementPlayer.setString(1, name);
                ResultSet resultSetPlayer = preparedStatementPlayer.executeQuery();
                while (resultSetPlayer.next()) {
                    UUID uuid = UUID.fromString(resultSetPlayer.getString("uuid"));
                    cdiTeam.getMembers().add(uuid);
                }
                plugin.getLogger().info("Team " + name + " loaded");
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public static void updateAllTeamsToDB() {
        for(CDITeam cdiTeam : CDILandPlugin.instance().teamManager().getTeams()) {
            try {
                Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
                long money = cdiTeam.getMoney();
                int supportPoints = cdiTeam.getSupportPoints();
                int slots = cdiTeam.getSlots();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE TEAM SET golds = ?, supportPoints = ?, Slots = ? WHERE name = ?");
                preparedStatement.setLong(1, money);
                preparedStatement.setInt(2, supportPoints);
                preparedStatement.setInt(3, slots);
                preparedStatement.setString(4, cdiTeam.getName());
                preparedStatement.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
