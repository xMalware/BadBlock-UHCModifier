package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import net.minecraft.server.v1_8_R3.BlockCarrots;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Items;

public class OBlockCarrots extends BlockCarrots {
	public OBlockCarrots() {
		c("carrots");
	}
	
	@Override
    protected Item n() {
        return Items.GOLDEN_CARROT;
    }
	
	@Override
	protected Item l() {
        return Items.GOLDEN_CARROT;
    }
}
