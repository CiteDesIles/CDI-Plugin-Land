package fr.citedesiles.cdiland.corruption;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class CorruptionManager {
    List<Corruption> corruptions = new ArrayList<>();

    public void tick() {
        for(Corruption corruption : corruptions) {
            corruption.tick();
        }
    }

    public void createCorruption(String id, Location location) {
        Corruption corruption = new Corruption(id, location);
        corruption.spawnHeart();
        corruptions.add(corruption);
    }

    public Corruption getCorruption(String id) {
        for(Corruption corruption : corruptions) {
            if(corruption.getId().equals(id)) {
                return corruption;
            }
        }
        return null;
    }

    public void removeCorruption(String id) {
        Corruption corruption = getCorruption(id);
        if(corruption != null) {
            corruptions.remove(corruption);
        }
    }
}
