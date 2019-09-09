package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import java.util.Random;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockGravel;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Items;

public class OBlockGravel extends BlockGravel {
	public OBlockGravel() {
		this.c(0.6F);
		this.a(Block.g);
		this.c("gravel");
	}
	
	@Override
    public Item getDropType(IBlockData iblockdata, Random random, int i) {
		return Items.ARROW;
    }

	@Override
	public int getDropCount(int i, Random random) {
		return 2;
    }
}
