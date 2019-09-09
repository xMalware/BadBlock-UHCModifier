package fr.badblock.bukkit.games.uhcmodifier.listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.badblock.bukkit.games.uhcmodifier.UHCModifier;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.WorldServer;

public class BlockBreakListener implements Listener {
	private Random random = new Random();

	@EventHandler
	public void onBreakBlock(BlockBreakEvent e){
		if(UHCModifier.config.oneShotTree && isTree(e.getBlock())){
			int min = 1000, max = -1000;

			for(Block block : breakArround(e.getBlock())){
				if(block.getY() < min)
					min = block.getY();
				if(block.getY() > max)
					max = block.getY();
			}

			despawnLeaves(e.getBlock(), min - 2, max + 6);
		}
	}

	private List<Block> breakArround(Block b){
		List<Block> blocks = new ArrayList<>();

		b.breakNaturally();
		blocks.add(b);

		int i = 0;
		for(BlockFace face : BlockFace.values()){
			Block newBlock = b.getRelative(face);

			if(isTree(newBlock)){
				i++;
				if (i >= 30)
				{
					continue;
				}
				newBlock.breakNaturally();
				blocks.addAll(breakArround(newBlock));
			}
		}

		return blocks;
	}

	private void despawnLeaves(Block b, int min, int max){
		List<BlockPosition> blocks = new ArrayList<>();

		WorldServer world = ((CraftWorld) b.getWorld()).getHandle();

		int n = 0;
		for(int x = (b.getX() - 5); x < (b.getX() + 5); x++){
			for(int y = min; y < max; y++){
				for(int z = (b.getZ() - 5); z < (b.getZ() + 5); z++){
					Block newBlock = b.getWorld().getBlockAt(x, y, z);

					if(isLeave(newBlock)){
						n++;
						if (n >= 30)
						{
							blocks.add(new BlockPosition(newBlock.getX(), newBlock.getY(), newBlock.getZ()));
						}
					}
				}
			}
		}

		Collections.shuffle(blocks);
		int countPerTick = blocks.size() / 10;

		new BukkitRunnable() {

			private int begin = 0;

			@Override
			public void run() {
				int beg = begin, 
						end = begin = countPerTick + beg;

				if(end >= blocks.size()){
					end = blocks.size();
					cancel();
				}

				if(end <= beg)
					return;

				for(int i=beg;i<end;i++){
					BlockPosition pos  = blocks.get(i);
					IBlockData    data = world.getChunkAtWorldCoords(pos).getBlockData(pos);

					data.getBlock().b(world, pos, data, random);
				}
			}

		}.runTaskTimer(UHCModifier.instance, 1L, 1L);
	}

	private boolean isTree(Block b){
		return b.getType() == Material.LOG || b.getType() == Material.LOG_2;
	}

	private boolean isLeave(Block b){
		return b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2;
	}
}
