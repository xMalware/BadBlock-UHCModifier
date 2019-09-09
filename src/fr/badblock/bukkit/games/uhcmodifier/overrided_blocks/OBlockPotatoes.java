package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import net.minecraft.server.v1_8_R3.BlockPotatoes;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Items;

public class OBlockPotatoes extends BlockPotatoes {
	public OBlockPotatoes() {
		this.c("potatoes");
	}
	
	@Override
    protected Item n() {
        return Items.BAKED_POTATO;
    }
	
	@Override
	protected Item l() {
        return Items.BAKED_POTATO;
    }
}
