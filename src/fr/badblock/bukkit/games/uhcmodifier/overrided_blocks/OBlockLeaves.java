package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import net.minecraft.server.v1_8_R3.BlockLeaves1;
import net.minecraft.server.v1_8_R3.BlockLeaves2;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Items;
import net.minecraft.server.v1_8_R3.World;

public class OBlockLeaves extends BlockLeaves1 {
	@Override
	protected void a(World world, BlockPosition blockposition, IBlockData iblockdata, int i) {
		if (world.random.nextInt(i) == 0) {
			Item type = world.random.nextInt(12) == 0 ? Items.GOLDEN_APPLE : Items.APPLE;
			a(world, blockposition, new ItemStack(type, 1, 0));
		}
	}
	
	@Override
	public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
        if (!world.isClientSide) {
            this.a(world, blockposition, iblockdata, 120);
        }
    }
	
	public static class OBlockLeaves2 extends BlockLeaves2 {
		@Override
		protected void a(World world, BlockPosition blockposition, IBlockData iblockdata, int i) {
			if (world.random.nextInt(i) == 0) {
				Item type = world.random.nextInt(12) == 0 ? Items.GOLDEN_APPLE : Items.APPLE;
				a(world, blockposition, new ItemStack(type, 1, 0));
			}
		}
		
		@Override
		public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
	        if (!world.isClientSide) {
	            this.a(world, blockposition, iblockdata, 120);
	        }
	    }
	}
}
