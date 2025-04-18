package fr.citedesiles.cdiland.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveKitUtility {
    public static void giveKit(Player player) {
        player.getInventory().addItem(
            new ItemStack(Material.IRON_HELMET),
            new ItemStack(Material.IRON_CHESTPLATE),
            new ItemStack(Material.IRON_LEGGINGS),
            new ItemStack(Material.IRON_BOOTS),
            new ItemStack(Material.IRON_SWORD),
            new ItemStack(Material.IRON_PICKAXE),
            new ItemStack(Material.COOKED_BEEF, 16)
        );
    }
}