package fr.badblock.bukkit.games.uhcmodifier.overrided_items;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.Blocks;
import net.minecraft.server.v1_8_R3.ItemPickaxe;
import net.minecraft.server.v1_8_R3.ItemStack;

public class OItemPickaxe extends ItemPickaxe {
	public OItemPickaxe(EnumToolMaterial item_enumtoolmaterial) {
		super(item_enumtoolmaterial);

	}

	@Override
    public boolean canDestroySpecialBlock(Block block) {
    	return block == Blocks.OBSIDIAN ? this.b.d() >= 2 : true;
    }

	
	@Override
    public float getDestroySpeed(ItemStack itemstack, Block block) {
		float speed = super.getDestroySpeed(itemstack, block);

		if(block == Blocks.OBSIDIAN && canDestroySpecialBlock(block)){
			speed = 1500.0f;
		}
		
		return speed;
	}
}
