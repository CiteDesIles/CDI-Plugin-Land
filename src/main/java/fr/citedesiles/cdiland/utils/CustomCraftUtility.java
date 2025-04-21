package fr.citedesiles.cdiland.utils;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.customItems.ItemManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class CustomCraftUtility {
    public void registerCustomCrafts() {

        NamespacedKey bonbonviolettekey = new NamespacedKey(CDILandPlugin.instance(), "bonbonviolette");
        ItemManager itemManager = new ItemManager();
        ShapedRecipe bonbonVioletterecipe = new ShapedRecipe(bonbonviolettekey, itemManager.get("bonbonViolette"));
        bonbonVioletterecipe.shape("ABA", "BAB", "ABA");
        bonbonVioletterecipe.setIngredient('A', Material.SUGAR);
        bonbonVioletterecipe.setIngredient('B', Material.PURPLE_DYE);
        CDILandPlugin.instance().getServer().addRecipe(bonbonVioletterecipe);

        NamespacedKey slimeballKey = new NamespacedKey(CDILandPlugin.instance(), "slimeball");
        ShapelessRecipe slimeballrecipe = new ShapelessRecipe(slimeballKey, new ItemStack(Material.SLIME_BALL));
        slimeballrecipe.addIngredient(new ItemStack(Material.HONEYCOMB));
        slimeballrecipe.addIngredient(new ItemStack(Material.ROTTEN_FLESH));
        CDILandPlugin.instance().getServer().addRecipe(slimeballrecipe);

        NamespacedKey oxidizedTrapdoor = new NamespacedKey(CDILandPlugin.instance(), "oxidized_trapdoor");
        ShapelessRecipe oxidizedTrapdoorRecipe = new ShapelessRecipe(oxidizedTrapdoor, new ItemStack(Material.OXIDIZED_COPPER_TRAPDOOR, 10));
        oxidizedTrapdoorRecipe.addIngredient(new ItemStack(Material.OXIDIZED_COPPER, 6));
        CDILandPlugin.instance().getServer().addRecipe(oxidizedTrapdoorRecipe);
    }
}
