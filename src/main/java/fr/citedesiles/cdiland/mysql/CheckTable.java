package fr.citedesiles.cdiland.mysql;

import fr.citedesiles.cdiland.CDILandPlugin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class CheckTable {

    public static void checkTables() {
        CompletableFuture.runAsync(() -> {
            checkTeamTableExist();
            checkPlayerTableExist();
            checkTableObjectifExist();
        });
    }

    public static void checkTableObjectifExist() {
        try {
            Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SHOW TABLES LIKE 'OBJECTIF'");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CDILandPlugin.instance().getLogger().info("Table OBJECTIF exists");
            } else {
                CDILandPlugin.instance().getLogger().info("Table OBJECTIF does not exist");
                createObjectifTable();
            }
            connection.close();
        } catch (SQLException e) {
            CDILandPlugin.instance().getLogger().severe("Error while checking if table OBJECTIF exists" + e.getMessage());
        }
    }

    public static void checkTeamTableExist() {
        try {
            Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SHOW TABLES LIKE 'TEAM'");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CDILandPlugin.instance().getLogger().info("Table TEAM exists");
            } else {
                CDILandPlugin.instance().getLogger().info("Table TEAM does not exist");
                createTeamTable();
            }
            connection.close();
        } catch (SQLException e) {
            CDILandPlugin.instance().getLogger().severe("Error while checking if table TEAM exists" + e.getMessage());
        }
    }

    public static void checkPlayerTableExist() {
        try {
            Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SHOW TABLES LIKE 'PLAYER'");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CDILandPlugin.instance().getLogger().info("Table PLAYER exists");
            } else {
                CDILandPlugin.instance().getLogger().info("Table PLAYER does not exist");
                createPlayerTable();
            }
            connection.close();
        } catch (SQLException e) {
            CDILandPlugin.instance().getLogger().severe("Error while checking if table PLAYER exists" + e.getMessage());
        }
    }

    private static void createObjectifTable() {
        /*
        Table Objectif:
        - team VARCHAR(255) PRIMARY KEY
        - objectif VARCHAR(255) PRIMARY KEY
        - value BIGINT
         */
        try {
            Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE OBJECTIF (team VARCHAR(255) NOT NULL, objectif VARCHAR(255) NOT NULL, value BIGINT)");
            preparedStatement.execute();
            CDILandPlugin.instance().getLogger().info("Table OBJECTIF has been created");
            connection.close();
        } catch (SQLException e) {
            CDILandPlugin.instance().getLogger().severe("Error while creating table OBJECTIF" + e.getMessage());
        }
    }

    public static void createTeamTable() {
        /*
        Table Team:
        - name VARCHAR(255) PRIMARY KEY
        - tag VARCHAR(255)
        - color VARCHAR(255)
        - team_leader VARCHAR(255)
        - display_name VARCHAR(255)
        - golds BIGINT
        - supportPoints BIGINT
        - Slots BIGINT
         */
        try {
            Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE TEAM (name VARCHAR(255) PRIMARY KEY, tag VARCHAR(255), color VARCHAR(255), team_leader VARCHAR(255),  display_name VARCHAR(255), golds BIGINT, supportPoints BIGINT, Slots BIGINT)");
            preparedStatement.execute();
            CDILandPlugin.instance().getLogger().info("Table TEAM has been created");
            connection.close();
        } catch (SQLException e) {
            CDILandPlugin.instance().getLogger().severe("Error while creating table TEAM" + e.getMessage());
        }
    }

    public static void createPlayerTable() {
        /*
        Table Player:
        - uuid VARCHAR(255) PRIMARY KEY
        - discordID VARCHAR(255)
        - team VARCHAR(255)
         */
        try {
            Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE PLAYER (uuid VARCHAR(255) PRIMARY KEY, discordID VARCHAR(255), team VARCHAR(255))");
            preparedStatement.execute();
            CDILandPlugin.instance().getLogger().info("Table PLAYER has been created");
            connection.close();
        } catch (SQLException e) {
            CDILandPlugin.instance().getLogger().severe("Error while creating table PLAYER" + e.getMessage());
        }
    }


    }
