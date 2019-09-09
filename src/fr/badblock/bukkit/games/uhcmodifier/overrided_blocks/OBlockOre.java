package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import java.util.Random;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockOre;
import net.minecraft.server.v1_8_R3.Blocks;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Items;
import net.minecraft.server.v1_8_R3.World;

public class OBlockOre extends BlockOre {
	public OBlockOre(String key) {
		this.c(3.0F);
		this.b(5.0f);
		this.a(Block.i);
		this.c(key);
	}
	
	@Override
    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        if(this == Blocks.COAL_ORE){
        	return Item.d("torch");
        } else if(this == Blocks.GOLD_ORE) {
        	return Items.GOLD_INGOT;
        } else if(this == Blocks.IRON_ORE) {
        	return Items.IRON_INGOT;
        }
    
        return super.getDropType(iblockdata, random, i);
	}
	
	@Override
	public int getDropCount(int i, Random random) {
		int count = super.getDropCount(i, random);
		
		 if(this == Blocks.COAL_ORE || this == Blocks.GOLD_ORE || this == Blocks.DIAMOND_ORE || this == Blocks.LAPIS_ORE || this == Blocks.IRON_ORE){
			 return count * 2;
		 }
		
		 return count;
	}
	
	@Override
	public int getExpDrop(World world, IBlockData iblockdata, int i){
		return super.getExpDrop(world, iblockdata, i) * 2; // on double l'exp drop
	}
}
