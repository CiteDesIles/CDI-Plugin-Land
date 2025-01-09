package fr.citedesiles.cdiland.mysql;


import fr.citedesiles.cdiland.CDILandPlugin;

public enum DatabaseManager {

    MAIN_DB(new DatabaseCredentials(
        CDILandPlugin.instance().configManager().getString("database.host"),
        CDILandPlugin.instance().configManager().getString("database.name"),
        CDILandPlugin.instance().configManager().getString("database.user"),
        CDILandPlugin.instance().configManager().getString("database.password"),
        CDILandPlugin.instance().configManager().getInt("database.port")
    ));

    private DatabaseAccess databaseAccess;

    DatabaseManager(DatabaseCredentials credentials) {
        this.databaseAccess = new DatabaseAccess(credentials);
    }

    public DatabaseAccess getDatabaseAccess() {
        return databaseAccess;
    }

    public static void initAllDataBaseConnections() {
        for(DatabaseManager databaseManager : values()) {
            databaseManager.getDatabaseAccess().initPool();
        }
    }

    public static void closeAllDataBaseConnections() {
        for(DatabaseManager databaseManager : values()) {
            databaseManager.getDatabaseAccess().closePool();
        }
    }
}
