package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockObsidian;

public class OBlockObsidian extends BlockObsidian {
	public OBlockObsidian(){
		this.c(1.5F);
		this.b(10.0F);
		this.a(Block.i);
		this.c("obsidian");
	}
}
