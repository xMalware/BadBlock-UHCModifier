package fr.badblock.bukkit.games.uhcmodifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

import fr.badblock.bukkit.games.uhcmodifier.utils.Reflector;
import net.minecraft.server.v1_8_R3.PotionBrewer;

public class RecipeModifier {
	private static final List<ItemStack> blacklistedItems = new ArrayList<>();
	private static final List<Recipe>    addRecipes       = new ArrayList<>();
	
	static {
		if(UHCModifier.config.disableNotchianGoldenApple){
			blacklistedItems.add( new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1) );
		}
		
		blacklistedItems.add(new ItemStack(Material.SPECKLED_MELON));
		
		Arrays.stream(UHCModifier.config.disabledItems).map(i -> new ItemStack(i)).forEach(blacklistedItems::add);
		
		ShapelessRecipe goldenMelon = new ShapelessRecipe(new ItemStack(Material.SPECKLED_MELON));
		goldenMelon.addIngredient(1, Material.GOLD_BLOCK);
		goldenMelon.addIngredient(1, Material.MELON);
		
		addRecipes.add(goldenMelon);
	}
	
	
	@SuppressWarnings("unchecked")
	public static void doJob() throws Exception {
		// enlève les potions level 2
		if(UHCModifier.config.disablePotionLevel2)
			((Map<Integer, String>) new Reflector(null, PotionBrewer.class).getFieldValue("effectAmplifiers")).clear();

		/*Bukkit.getRecipesFor(new ItemStack(Material.SPECKLED_MELON)).clear();
		Bukkit.getRecipesFor(new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1)).clear();*/
		
		blacklistedItems.forEach(item -> Bukkit.getRecipesFor(item).clear());
		
		List<Recipe> allRecipes = new ArrayList<>();
		Iterator<Recipe> recipes = Bukkit.recipeIterator();
		
		while(recipes.hasNext()){
			Recipe recipe = recipes.next();
			
			if(!blacklistedItems.contains(recipe.getResult())){
				allRecipes.add(recipe);
			} else {
				System.out.println(recipe.getResult().getType());
			}
		}
		
		Bukkit.clearRecipes();
		allRecipes.addAll(addRecipes);
		
		allRecipes.forEach(Bukkit::addRecipe);
	}
}
