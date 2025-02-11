package fr.citedesiles.cdiland.mysql;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objectif.CDIObjectif;
import fr.citedesiles.cdiland.objectif.CDIObjectifCycleReset;
import fr.citedesiles.cdiland.objectif.CDIObjectifTransactionCheck;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SyncObjectifSQL {
    public static void syncToDB() {
        for(CDIObjectif cdiObjectif : CDILandPlugin.instance().objectifManager().objectifs()) {
            // Check if exist in DB, if not create and set value to value
            // Column team, objectif, value
            try {
                Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
                ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM objectifs WHERE objectif = '" + cdiObjectif.getName() + "' AND team = '" + cdiObjectif.getTeamName() + "'");
                if(!resultSet.next()) {
                    connection.createStatement().executeUpdate("INSERT INTO objectifs (team, objectif, value) VALUES ('" + cdiObjectif.getTeamName() + "', '" + cdiObjectif.getName() + "', " + cdiObjectif.getValue() + ")");
                }
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if(cdiObjectif instanceof CDIObjectifCycleReset) {
                // Add value to the value in DB
                try {
                    Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
                    connection.createStatement().executeUpdate("UPDATE objectifs SET value = value + " + cdiObjectif.getValue() + " WHERE objectif = '" + cdiObjectif.getName() + "' AND team = '" + cdiObjectif.getTeamName() + "'");
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                cdiObjectif.setValue(0);
            }
            if(cdiObjectif instanceof CDIObjectifTransactionCheck) {
                // If value var is superior to value in DB, set value in DB from value var
                try {
                    Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
                    ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM objectifs WHERE objectif = '" + cdiObjectif.getName() + "' AND team = '" + cdiObjectif.getTeamName() + "'");
                    if(resultSet.next()) {
                        if(cdiObjectif.getValue() > resultSet.getInt("value")) {
                            connection.createStatement().executeUpdate("UPDATE objectifs SET value = " + cdiObjectif.getValue() + " WHERE objectif = '" + cdiObjectif.getName() + "' AND team = '" + cdiObjectif.getTeamName() + "'");
                        }
                    }
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
