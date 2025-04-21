package fr.citedesiles.cdiland.corruption;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;

public class CorruptionEntities {
    public static void spawnCorruptedZombie(Location location) {
        Zombie zombie = location.getWorld().spawn(location, Zombie.class);
        zombie.setCustomName("§1§lZombie corrompu");
        zombie.setCustomNameVisible(true);
        zombie.getAttribute(Attribute.MAX_HEALTH).setBaseValue(40);
        zombie.getAttribute(Attribute.SCALE).setBaseValue(1.5);
        zombie.setHealth(40);
    }

    public static void spawnCorruptedSkeleton(Location location) {
        Skeleton skeleton = location.getWorld().spawn(location, Skeleton.class);
        skeleton.setCustomName("§1§lSquelette corrompu");
        skeleton.setCustomNameVisible(true);
        skeleton.getAttribute(Attribute.MAX_HEALTH).setBaseValue(40);
        skeleton.getAttribute(Attribute.SCALE).setBaseValue(1.5);
        skeleton.setHealth(40);
    }
}
