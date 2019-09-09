package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import java.util.Random;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockMushroom;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Items;

public class OBlockMushroom extends BlockMushroom {
	public OBlockMushroom(boolean brown) {
		this.c(0.0F);
		this.a(Block.h);
		this.c("mushroom");
		
		if(brown)
			this.a(0.125F);
	}
	
	@Override
	public Item getDropType(IBlockData iblockdata, Random random, int i) {
		return Items.MUSHROOM_STEW;
    }
	
	@Override
	public int getDropData(IBlockData iblockdata) {
		return 0;
	}
	
	@Override
	public int getDropCount(int i, Random random) {
        return 2;
    }
}
