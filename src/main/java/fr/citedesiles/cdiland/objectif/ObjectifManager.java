package fr.citedesiles.cdiland.objectif;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objects.CDITeam;

import java.util.ArrayList;
import java.util.List;

public class ObjectifManager {
    List<CDIObjectif> objectifs = new ArrayList<>();

    public ObjectifManager() {
        for(CDITeam team : CDILandPlugin.instance().teamManager().getTeams()) {
            objectifs.add(new CDIObjectifCycleReset("dragon_slayer", team.getName()));
            objectifs.add(new CDIObjectifTransactionCheck("angel", team.getName()));
            objectifs.add(new CDIObjectifTransactionCheck("dig", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("dirt", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("death", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("kill", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("ender_pearl", team.getName())); // Replaced by fished item count
            objectifs.add(new CDIObjectifCycleReset("netherite_houe", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("totem_of_undying", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("toolbroken", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("wardenkilled", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("honey", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("arrow_shoot_on_player", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("axolotl_nlue_killed", team.getName())); // Replaced by wither skeleton killed
        }
    }

    public List<CDIObjectif> objectifs() {
        return new ArrayList<>(objectifs);
    }

    public CDIObjectif get(CDITeam team, String name) {
        for(CDIObjectif objectif : objectifs) {
            if(objectif.getName().equals(name) && objectif.getTeamName().equals(team.getName())) {
                return objectif;
            }
        }
        return null;
    }
}
