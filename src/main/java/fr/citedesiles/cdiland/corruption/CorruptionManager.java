package fr.citedesiles.cdiland.corruption;

import java.util.ArrayList;
import java.util.List;

public class CorruptionManager {
    List<Corruption> corruptions = new ArrayList<>();

    public void tick() {
        for(Corruption corruption : corruptions) {
            corruption.tick();
        }
    }
}
