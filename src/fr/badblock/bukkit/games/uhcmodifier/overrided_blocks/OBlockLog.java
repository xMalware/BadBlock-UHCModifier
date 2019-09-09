package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import java.util.Random;

import net.minecraft.server.v1_8_R3.BlockLog1;
import net.minecraft.server.v1_8_R3.BlockLog2;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.Item;

public class OBlockLog extends BlockLog1 {
	public OBlockLog(){
		this.c("log");
	}
	
	@Override
	public Item getDropType(IBlockData iblockdata, Random random, int i){
		return Item.getById(17);
	}
	
	@Override
	public int getDropData(IBlockData iblockdata) {
		return 0;
    }
	
	public static class OBlockLog2 extends BlockLog2 {
		public OBlockLog2(){
			this.c("log");
		}
		
		@Override
		public Item getDropType(IBlockData iblockdata, Random random, int i){
			return Item.getById(17);
		}
		
		@Override
		public int getDropData(IBlockData iblockdata) {
			return 0;
	    }
	}

}
