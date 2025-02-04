package fr.citedesiles.cdiland.corruption;

import fr.citedesiles.cdiland.CDILandPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Corruption {

    public final static Material CORRUPTED_BLOCK = Material.SCULK;

    private int radius;
    private int speed;
    private Location center;
    private List<CorruptionBlock> corruptionBlocks;

    private int currentTickToNextBlock = 0;
    private int removeBlockPerTick = 100;


    public Corruption(Location center, int speed) {
        this.radius = 0;
        this.speed = speed;
        this.corruptionBlocks = new ArrayList<>();
        this.center = center;
    }

    public void spawnHeart() {
        // Sphère en obsidienne de 3 de rayons --> Coeur de la corruption
        for(int x = -3; x <= 3; x++) {
            for(int y = -3; y <= 3; y++) {
                for(int z = -3; z <= 3; z++) {
                    Location location = center.clone().add(x, y, z);
                    if(location.distance(center) <= 3) {
                        Block block = location.getBlock();
                        if(block.getType() != Material.AIR) {
                            CorruptionBlock corruptionBlock = new CorruptionBlock(block.getType(), location);
                            corruptionBlocks.add(corruptionBlock);
                            block.setType(CORRUPTED_BLOCK);
                        }
                    }
                }
            }
        }
    }

    public void tick() {
        if(currentTickToNextBlock < speed) {
            currentTickToNextBlock++;
            return;
        }
        // TODO: Implement this.
    }

    public Block getBlockNearARandomCorruptedBlock() {
        List<CorruptionBlock> corruptionBlocks1 = new ArrayList<>(corruptionBlocks);
        Collections.shuffle(corruptionBlocks1);
        List<String> directions = new ArrayList<>();
        directions.add("UP");
        directions.add("DOWN");
        directions.add("NORTH");
        directions.add("SOUTH");
        directions.add("EAST");
        directions.add("WEST");
        Collections.shuffle(directions);
        for(CorruptionBlock corruptionBlock : corruptionBlocks1) {
            for(String direction : directions) {
                Block block = null;
                switch (direction) {
                    case "UP" -> {
                        block = corruptionBlock.getLocation().add(0, 1, 0).getBlock();
                    }
                    case "DOWN" -> {
                        block = corruptionBlock.getLocation().add(0, -1, 0).getBlock();
                    }
                    case "NORTH" -> {
                        block = corruptionBlock.getLocation().add(0, 0, -1).getBlock();
                    }
                    case "SOUTH" -> {
                        block = corruptionBlock.getLocation().add(0, 0, 1).getBlock();
                    }
                    case "EAST" -> {
                        block = corruptionBlock.getLocation().add(1, 0, 0).getBlock();
                    }
                    case "WEST" -> {
                        block = corruptionBlock.getLocation().add(-1, 0, 0).getBlock();
                    }
                }
                if(block == null) {
                    continue;
                }
                if(block.getType() != Material.AIR &&
                    !block.getType().toString().contains("SCULK") &&
                    !block.getType().toString().contains("BEDROCK") &&
                    !block.getType().toString().contains("OBSIDIAN"))
                {
                        return block;
                }
            }
        }
        return null;
    }

    public Boolean isBlockOnTopOfWorld(Block block) {
        return (block.getWorld().getHighestBlockAt(block.getLocation()).equals(block));
    }

    public Boolean isInCorruption(Location location) {
        return (location.distance(center) <= radius);
    }

    public void removeCorruption() {
        CDILandPlugin.instance().getServer().getScheduler().runTaskTimer(CDILandPlugin.instance(), (task) -> {
            if(corruptionBlocks.isEmpty()) {
                task.cancel();
            }
            int count = 0;
            List<CorruptionBlock> corruptionBlocks1 = new ArrayList<>(corruptionBlocks);
            Collections.shuffle(corruptionBlocks1);
            for(CorruptionBlock corruptionBlock : corruptionBlocks1) {
                if(removeBlockPerTick > count) {
                    corruptionBlocks.remove(corruptionBlock);
                    corruptionBlock.getLocation().getBlock().setType(corruptionBlock.getAncientBlock());
                    count++;
                }
            }
        }, 0, 5);
    }






}
