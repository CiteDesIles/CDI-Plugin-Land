package fr.citedesiles.cdiland.corruption;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CorruptionManager {
    List<Corruption> corruptions = new ArrayList<>();

    public void tick() {
        for(Corruption corruption : corruptions) {
            corruption.tick();
        }
    }

    public void createCorruption(String id, Location location, int speed) {
        Corruption corruption = new Corruption(id, location, speed);
        corruption.spawnHeart();
        corruptions.add(corruption);
    }
}
