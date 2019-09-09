package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import java.util.Random;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockReed;

public class OBlockReed extends BlockReed {
	public OBlockReed() {
		this.a(Block.h);
		this.c("reeds");
		this.K();
	}
	
	@Override
	public int getDropCount(int i, Random random) {
        return random.nextInt(2) + 1;
    }

}
