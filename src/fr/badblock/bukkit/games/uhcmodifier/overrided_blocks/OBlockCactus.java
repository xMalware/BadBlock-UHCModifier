package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import java.util.Random;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockCactus;
import net.minecraft.server.v1_8_R3.BlockWood;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.World;

public class OBlockCactus extends BlockCactus {
	public OBlockCactus() {
		this.c(0.4F);
		this.a(Block.l);
		this.c("cactus");
	}
	
	@Override
	public Item getDropType(IBlockData iblockdata, Random random, int i) {
		System.out.println("coucou :o");
		
        return Item.d("log");
    }
	
	@Override
	public int getDropData(IBlockData iblockdata) {
		return BlockWood.EnumLogVariant.OAK.a(); // current : 0
    }
	
	@Override
	public int getDropCount(int i, Random random) {
        return 1;
    }
	
	@Override
    public int getExpDrop(World world, IBlockData iblockdata, int i) {
        return 0;
    }
}
