package fr.citedesiles.cdiland.objectif;

public abstract class CDIObjectif {
    public String name;
    public String teamName;
    public int value;

    public CDIObjectif(String name, String teamName, int value) {
        this.name = name;
        this.teamName = teamName;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return this.name;
    }


}
