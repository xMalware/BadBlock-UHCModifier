package fr.badblock.bukkit.games.uhcmodifier;

import org.bukkit.Material;

import net.minecraft.server.v1_8_R3.Item;

public class MaxItemSize {
	@SuppressWarnings("deprecation")
	public static void setMaxItemStackSize(Material type, int max){
		Item item = Item.getById(type.getId());
		item.c(max);
	}
	
	public static void doJob(){
		//TODO voir quoi faire
	}
}
