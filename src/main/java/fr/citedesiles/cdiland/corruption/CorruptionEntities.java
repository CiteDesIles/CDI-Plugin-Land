package fr.citedesiles.cdiland.corruption;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;

public class CorruptionEntities {
    public void spawnCorruptedZombie(Location location) {
        Zombie zombie = location.getWorld().spawn(location, Zombie.class);
        zombie.setCustomName("§1§lZombie corrompu");
        zombie.setCustomNameVisible(true);
        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        zombie.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(1.5);
        zombie.setHealth(40);
    }

    public void spawnCorruptedSkeleton(Location location) {
        Skeleton skeleton = location.getWorld().spawn(location, Skeleton.class);
        skeleton.setCustomName("§1§lSquelette corrompu");
        skeleton.setCustomNameVisible(true);
        skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        skeleton.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(1.5);
        skeleton.setHealth(40);
    }
}
