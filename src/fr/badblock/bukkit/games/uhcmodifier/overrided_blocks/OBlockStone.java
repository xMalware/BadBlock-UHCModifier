package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import java.util.Random;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockStone;
import net.minecraft.server.v1_8_R3.Blocks;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.Item;

public class OBlockStone extends BlockStone {
	public OBlockStone(){
		this.c(1.5F);
		this.b(10.0F);
		this.a(Block.i);
		this.c("stone");
	}
	
	@Override
	public Item getDropType(IBlockData iblockdata, Random random, int i) {
		return Item.getItemOf(Blocks.COBBLESTONE);
    }

	@Override
    public int getDropData(IBlockData iblockdata) {
		return 0;
	}
}

