package fr.citedesiles.cdiland.objectif;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objects.CDITeam;

import java.util.ArrayList;
import java.util.List;

public class ObjectifManager {
    List<CDIObjectif> objectifs = new ArrayList<CDIObjectif>();

    public ObjectifManager() {
        for(CDITeam team : CDILandPlugin.instance().teamManager().getTeams()) {
            objectifs.add(new CDIObjectifCycleReset("dragon_slayer", team.getName()));
            objectifs.add(new CDIObjectifTransaction("angel", team.getName()));
            objectifs.add(new CDIObjectifTransaction("dig", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("dirt", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("death", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("kill", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("ender_pearl", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("netherite_houe", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("totem_of_undying", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("toolbroken", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("wardenkilled", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("honey", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("arrow_shoot_on_player", team.getName()));
            objectifs.add(new CDIObjectifCycleReset("axolotl_nlue_killed", team.getName()));
        }
    }
}
