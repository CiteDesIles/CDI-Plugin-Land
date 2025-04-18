package fr.citedesiles.cdiland.customItems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemManager {
    Map<String, ItemStack> customsItems = new HashMap<>();

    public List<ItemStack> getCustomsItems() {
        return new ArrayList<>(customsItems.values());
    }

    public ItemManager() {
        ItemStack bonbonViolette = new ItemStack(Material.PURPLE_DYE, 1);
        ItemMeta bonbonVioletteMeta = bonbonViolette.getItemMeta();
        bonbonVioletteMeta.setDisplayName("§5§lBonbon à la violette");
        bonbonVioletteMeta.setCustomModelData(2);
        bonbonViolette.setItemMeta(bonbonVioletteMeta);
        customsItems.put("bonbonViolette", bonbonViolette);
    }

    public ItemStack get(String name) {
        return customsItems.get(name);
    }
}