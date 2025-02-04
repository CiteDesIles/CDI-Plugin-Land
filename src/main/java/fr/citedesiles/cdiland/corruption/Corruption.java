package fr.citedesiles.cdiland.corruption;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Corruption {
    private int radius;
    private int speed;
    private Location center;
    private List<CorruptionBlock> corruptionBlocks;

    private int currentTickToNextBlock = 0;


    public Corruption(Location center, int speed) {
        this.radius = 0;
        this.speed = speed;
        this.corruptionBlocks = new ArrayList<>();
        this.center = center;
    }

    public void tick() {
        if(currentTickToNextBlock < speed) {
            currentTickToNextBlock++;
            return;
        }
        // TODO: Implement this.
    }

    public Block getBlockNearACorruptedBlock() {
        // TODO: Implement this.
        return null;
    }

    public Boolean isBlockOnTopOfWorld() {
        // TODO: Implement this.
        return false;
    }

    public Boolean isInCorruption(Location location) {
        return (location.distance(center) <= radius);
    }






}
