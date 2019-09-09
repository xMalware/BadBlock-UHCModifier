package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import java.util.Random;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockSand;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Items;

public class OBlockSand extends BlockSand {
	public OBlockSand() {
		this.c(0.5F);
		this.a(Block.m);
		this.c("sand");
	}
	
	@Override
	public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Items.GLASS_BOTTLE;
    }
	
	@Override
	public int getDropCount(int i, Random random) {
		return random.nextInt(2) + 1;
    }
}
