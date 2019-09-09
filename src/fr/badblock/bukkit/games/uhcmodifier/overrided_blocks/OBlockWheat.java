package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import net.minecraft.server.v1_8_R3.BlockCrops;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Items;

public class OBlockWheat extends BlockCrops {
	public OBlockWheat(){
		this.c("crops");
	}
	
	@Override
    protected Item n() {
        return Items.BREAD;
    }
	
	@Override
	protected Item l() {
        return Items.BREAD;
    }
}
