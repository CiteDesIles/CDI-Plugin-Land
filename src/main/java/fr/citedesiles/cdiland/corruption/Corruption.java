package fr.citedesiles.cdiland.corruption;

import fr.citedesiles.cdiland.CDILandPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Corruption {

    public final static Material CORRUPTED_BLOCK = Material.SCULK;

    private int radius;
    private int speed;
    private Location center;
    private List<CorruptionBlock> corruptionBlocks;

    private int currentTickToNextBlock = 0;
    private final int removeBlockPerTick = 100;
    private final int corruptionBlockPerTick = 100;
    private final int maxEntities = 100;

    private boolean destroyed = false;

    String id;

    public Corruption(String id, Location center, int speed) {
        this.radius = 0;
        this.speed = speed;
        this.corruptionBlocks = new ArrayList<>();
        this.center = center;
        this.id = id;
    }

    public void spawnHeart() {
        // Sphère en obsidienne de 3 de rayons --> Coeur de la corruption
        for(int x = -3; x <= 3; x++) {
            for(int y = -3; y <= 3; y++) {
                for(int z = -3; z <= 3; z++) {
                    Location location = center.clone().add(x, y, z);
                    if(location.distance(center) <= 2) {
                        Block block = location.getBlock();
                        block.setType(Material.OBSIDIAN);
                    }
                }
            }
        }

        // Corrompre les blocks dans un rayon de 8 blocks
        for(int x = -8; x <= 8; x++) {
            for(int y = -8; y <= 8; y++) {
                for(int z = -8; z <= 8; z++) {
                    Location location = center.clone().add(x, y, z);
                    if(location.distance(center) <= 8) {
                        Block block = location.getBlock();
                        if(block.getType() != Material.AIR && block.getType() != Material.BEDROCK && block.getType() != Material.OBSIDIAN) {
                            CorruptionBlock corruptionBlock = new CorruptionBlock(block.getType(), location);
                            corruptionBlocks.add(corruptionBlock);
                            block.setType(CORRUPTED_BLOCK);
                        }
                    }
                }
            }
        }
        this.radius = 10;
    }

    public void tick() {
        if(currentTickToNextBlock < speed) {
            currentTickToNextBlock++;
            return;
        }
        currentTickToNextBlock = 0;
        for(int i = 0; i < corruptionBlockPerTick; i++) {
            corruptBlock();
        }
        updateRadius();
        double random = (Math.random() * 100);
        if(random < 10) {
            spawnMonsters();
        }

        int countHeart = 0;
        for(int x = -3; x <= 3; x++) {
            for(int y = -3; y <= 3; y++) {
                for(int z = -3; z <= 3; z++) {
                    Location location = center.clone().add(x, y, z);
                    if(location.distance(center) <= 2) {
                        Block block = location.getBlock();
                        if(block.getType() == Material.OBSIDIAN) {
                            countHeart++;
                        }
                    }
                }
            }
        }
        if(countHeart == 0) {
            if(destroyed) {
                return;
            }
            Bukkit.broadcastMessage("§aLa corruption a été vaincue !");
            CDILandPlugin.instance().corruptionManager().removeCorruption(this.id);
            destroyed = true;
        }
    }

    public void spawnMonsters() {
        if(countEntitiesInCorruption() >= maxEntities) {
            return;
        }
        double x = Math.random() * radius * 2 - radius;
        double z = Math.random() * radius * 2 - radius;
        int y = center.getWorld().getHighestBlockYAt(center.clone().add(x, 0, z));
        Location location = center.clone().add(x, y, z);
        location.setY(y + 1);
        //if(isInCorruption(location)) {
        //    if(location.getBlock().getType() == Material.AIR && isBlockOnTopOfWorld(location.getBlock())) {
                if(Math.random() > 0.5) {
                    CorruptionEntities.spawnCorruptedZombie(location);
                } else {
                    CorruptionEntities.spawnCorruptedSkeleton(location);
                }
          //  }
        //}
    }

    public void updateRadius() {
        int distance = 0;
        for(CorruptionBlock corruptionBlock : corruptionBlocks) {
            if(corruptionBlock.getLocation().distance(center) > distance) {
                distance = (int) corruptionBlock.getLocation().distance(center);
            }
        }
        this.radius = distance -2;
    }

    private void corruptBlock() {
        Block block = getBlockNearARandomCorruptedBlock();
        if(block != null) {
            CorruptionBlock corruptionBlock = new CorruptionBlock(block.getType(), block.getLocation());
            corruptionBlocks.add(corruptionBlock);
            block.setType(CORRUPTED_BLOCK);
            if(isBlockOnTopOfWorld(block)) {
                double random = Math.random() * 100;
                if(random < 0.6) {
                    Block blockU = block.getLocation().add(0, 1, 0).getBlock();
                    blockU.setType(Material.SCULK_SENSOR);
                }
                double random2 = Math.random() * 100;
                if(random < 0.2) {
                    Block blockD = block.getLocation().add(0, 1, 0).getBlock();
                    blockD.setType(Material.SCULK_CATALYST);
                }
                double random3 = Math.random() * 100;
                if(random < 0.1) {
                    Block blockN = block.getLocation().add(0, 1, 0).getBlock();
                    blockN.setType(Material.SCULK_SHRIEKER);
                }
            }
        }
    }

    private void corruptBlock(Block block) {
        CorruptionBlock corruptionBlock = new CorruptionBlock(block.getType(), block.getLocation());
        corruptionBlocks.add(corruptionBlock);
        block.setType(CORRUPTED_BLOCK);
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
                CDILandPlugin.instance().corruptionManager().removeCorruption(id);
                task.cancel();
            }
            for(int i = 0; i < removeBlockPerTick; i++) {
                if(corruptionBlocks.isEmpty()) {
                    break;
                }
                CorruptionBlock corruptionBlock = corruptionBlocks.get(corruptionBlocks.size()-1);
                corruptionBlocks.remove(corruptionBlock);
                corruptionBlock.getLocation().getBlock().setType(corruptionBlock.getAncientBlock());
                Block blockU = corruptionBlock.getLocation().add(0, 1, 0).getBlock();
                if(blockU.getType() == Material.SCULK_SENSOR) {
                    blockU.setType(Material.AIR);
                }
                if(blockU.getType() == Material.SCULK_CATALYST) {
                    blockU.setType(Material.AIR);
                }
            }
        }, 0, 5);
    }

    public int countEntitiesInCorruption() {
        int count = 0;
        for(Entity entity : center.getWorld().getEntities()) {
            if(entity.getLocation().distance(center) <= radius) {
                if (entity instanceof Zombie || entity instanceof Skeleton) {
                    count++;
                }
            }
        }
        return count;
    }

    public String getId() {
        return id;
    }
}
