package fr.badblock.bukkit.games.uhcmodifier.overrided_items;

import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.ItemFood;
import net.minecraft.server.v1_8_R3.Item.EnumToolMaterial;
import net.minecraft.server.v1_8_R3.ItemGoldenApple;
import net.minecraft.server.v1_8_R3.MinecraftKey;
import net.minecraft.server.v1_8_R3.MobEffectList;

public enum OItems {
	STONE_PICKAXE(new OItemPickaxe(EnumToolMaterial.STONE).c("pickaxeStone"), "stone_pickaxe"),
	IRON_PICKAXE(new OItemPickaxe(EnumToolMaterial.IRON).c("pickaxeIron"), "iron_pickaxe"),
	GOLDEN_PICKAXE(new OItemPickaxe(EnumToolMaterial.GOLD).c("pickaxeGold"), "golden_pickaxe"),
	DIAMOND_PICKAXE(new OItemPickaxe(EnumToolMaterial.EMERALD).c("pickaxeDiamond"), "diamond_pickaxe"),
	GOLDEN_APPLE((new ItemGoldenApple(4, 1.2F, false)).h().a(MobEffectList.REGENERATION.id, 10, 1, 1.0F).c("appleGold"), "golden_apple"),
	COOKED_BEEF((new ItemFood(10, 0.8F, true)).c("beefCooked"), "cooked_beef"),
	COOKED_CHICKEN((new ItemFood(10, 0.8F, true)).c("chickenCooked"), "cooked_chicken"),
	COOKED_RABBIT((new ItemFood(10, 0.8F, true)).c("rabbitCooked"), "cooked_rabbit"),
	COOKED_MUTTON((new ItemFood(10, 0.8F, true)).c("muttonCooked"), "cooked_mutton"),
	COOKED_PORKCHOP((new ItemFood(10, 0.8F, true)).c("porkchopCooked"), "cooked_porkchop");

	private Item   newItem;
	private String minecraftKey;

	OItems(Item newItem, String minecraftKey){
		this.newItem 	  = newItem;
		this.minecraftKey = minecraftKey;
	}

	public static void register() throws Exception {
		for(OItems oitem : values()){
			Item old = Item.d(oitem.minecraftKey);
			int id = Item.getId(old);
			
			Item.REGISTRY.a(id, new MinecraftKey(oitem.minecraftKey), oitem.newItem);;
		}
	}
}

