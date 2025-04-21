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

    public static void spawnCorruptedWarden(Location location) {
        Skeleton warden = location.getWorld().spawn(location, Skeleton.class);
        warden.setCustomName("§1§lWarden corrompu");
        warden.setCustomNameVisible(true);
        warden.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
        warden.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(1.5);
        warden.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2);
        warden.setHealth(60);
    }
}
