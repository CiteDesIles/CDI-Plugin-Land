package fr.citedesiles.cdiland.corruption;

import org.bukkit.Location;
import org.bukkit.Material;

public class CorruptionBlock {
    private Material ancientBlock;
    private Location location;

    public CorruptionBlock(Material ancientBlock, Location location) {
        this.ancientBlock = ancientBlock;
        this.location = location;
    }
}
