package fr.citedesiles.cdiland.npc;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ChangeServeurMenu {
    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, "§c§lChanger de serveur");
        ItemStack cite = new ItemStack(Material.RED_CONCRETE);
        ItemMeta citeMeta = cite.getItemMeta();
        citeMeta.setDisplayName("§c§lCité");
        List<String> lore = new ArrayList<>();
        lore.add("§7Cliquez pour aller sur §c§lCité");
        citeMeta.setLore(lore);
        cite.setItemMeta(citeMeta);
        inv.setItem(4, cite);
        player.openInventory(inv);
    }
}
