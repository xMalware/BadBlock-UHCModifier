package fr.badblock.bukkit.games.uhcmodifier.listeners;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import fr.badblock.bukkit.games.uhcmodifier.UHCModifier;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.ItemTool;

public class PrepareItemCraftListener implements Listener {
	@EventHandler
	public void onPrepareItemCraft(PrepareItemCraftEvent e){
		ItemStack result = e.getInventory().getResult();
		
		if(result == null)
			return;
		
		if(UHCModifier.config.enchantTools && isItemTool(result)){
			if(isItemWoodTool(result)){
				setItemStoneTool(result);
			}
			
			result.addEnchantment(Enchantment.DIG_SPEED, 2);
			result.addEnchantment(Enchantment.DURABILITY, 1);
		} else return;
		
		e.getInventory().setResult(result);
	}
	
	private boolean isItemTool(ItemStack is){
		return CraftItemStack.asNMSCopy(is).getItem() instanceof ItemTool;
	}
	
	private ItemTool getTool(ItemStack is){
		return (ItemTool) CraftItemStack.asNMSCopy(is).getItem();
	}
	
	private void setItemStoneTool(ItemStack is){
		Material mat = Material.matchMaterial( is.getType().name().replace("WOOD", "IRON") );

		is.setType(mat);
	}
	
	private boolean isItemWoodTool(ItemStack is){
		return isItemTool(is) && getTool(is).g() == Item.EnumToolMaterial.WOOD;
	}
}
