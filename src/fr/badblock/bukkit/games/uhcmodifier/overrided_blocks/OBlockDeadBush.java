package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import java.util.Random;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockDeadBush;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Items;

public class OBlockDeadBush extends BlockDeadBush {
	public OBlockDeadBush() {
		this.c(0.0F);
		this.a(Block.h);
		this.c("deadbush");
	}
	
	@Override
	public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Items.BREAD;
    }
	
	@Override
	public int getDropCount(int i, Random random) {
        return 2;
    }
}
