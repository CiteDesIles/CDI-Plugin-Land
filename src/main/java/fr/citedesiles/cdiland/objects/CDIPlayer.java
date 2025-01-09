package fr.citedesiles.cdiland.objects;

import java.util.UUID;

public class CDIPlayer {
    private UUID uuid;
    private String discordID;
    private String team;

    public CDIPlayer(UUID uuid, String discordID, String team) {
        this.uuid = uuid;
        this.discordID = discordID;
        this.team = team;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean hasTeam() {
        return team != null;
    }
}
