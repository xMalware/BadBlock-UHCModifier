package fr.badblock.bukkit.games.uhcmodifier.overrided_blocks;

import fr.badblock.bukkit.games.uhcmodifier.overrided_blocks.OBlockLeaves.OBlockLeaves2;
import fr.badblock.bukkit.games.uhcmodifier.overrided_blocks.OBlockLog.OBlockLog2;
import fr.badblock.bukkit.games.uhcmodifier.utils.ReflectionUtils;
import fr.badblock.bukkit.games.uhcmodifier.utils.Reflector;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.Blocks;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.ItemBlock;
import net.minecraft.server.v1_8_R3.ItemMultiTexture;
import net.minecraft.server.v1_8_R3.MinecraftKey;

public enum OBlocks {
	CACTUS(new OBlockCactus(), "cactus"),
	CARROTS(new OBlockCarrots(), "carrots"),
	DEADBUSH(new OBlockDeadBush(), "deadbush"),
	GRAVEL(new OBlockGravel(), "gravel"),
	BROWN_MUSHROOM(new OBlockMushroom(true), "brown_mushroom"),
	RED_MUSHROOM(new OBlockMushroom(false), "red_mushroom"),
	DIAMOND_ORE(new OBlockOre("oreDiamond"), "diamond_ore"),
	IRON_ORE(new OBlockOre("oreIron"), "iron_ore"),
	GOLD_ORE(new OBlockOre("oreGold"), "gold_ore"),
	COAL_ORE(new OBlockOre("oreCoal"), "coal_ore"),
	LAPIS_ORE(new OBlockOre("oreLapis"), "lapis_ore"),
	POTATOES(new OBlockPotatoes(), "potatoes"),
	REEDS(new OBlockReed(), "reeds"),
	SAND(new OBlockSand(), "sand"),
	STONE(new OBlockStone(), "stone"),
	WHEAT(new OBlockWheat(), "wheat"),
	LOG_1(new OBlockLog(), "log"),
	LOG_2(new OBlockLog2(), "log2"),
	LEAVES_1(new OBlockLeaves().c("leaves"), "leaves"),
	LEAVES_2(new OBlockLeaves2().c("leaves"), "leaves2");

	private Block  newBlock;
	private String minecraftKey;

	OBlocks(Block newBlock, String minecraftKey){
		this.newBlock     = newBlock;
		this.minecraftKey = minecraftKey;
	}

	public static void register() throws Exception {
		Reflector 		  		 blocksList = new Reflector(null, Blocks.class);

		for(OBlocks oblock : values()){
			Block old = (Block) blocksList.getStaticFieldValue(oblock.minecraftKey.toUpperCase());

			int id = Block.getId(Block.getByName(oblock.minecraftKey));
			MinecraftKey key = new MinecraftKey(oblock.minecraftKey);

			Block.REGISTRY.a(id, key, oblock.newBlock);
			blocksList.setStaticFieldValue(oblock.minecraftKey.toUpperCase(), oblock.newBlock);

			int i = id << 4 | oblock.newBlock.toLegacyData(oblock.newBlock.getBlockData());
			Block.d.a(oblock.newBlock.getBlockData(), i);

			if(!repairItemBlock((ItemBlock) Item.getItemOf(old), oblock.newBlock)){
				ReflectionUtils.getMethod(Item.class, "c", Block.class).invoke(null, oblock.newBlock);
			}
		}
		
		repairRegistry();
	}
	
	private static void repairRegistry(){
        for(Block block : Block.REGISTRY) {
            for(IBlockData blockData : block.P().a()){
            	int i = Block.REGISTRY.b(block) << 4 | block.toLegacyData(blockData);

                Block.d.a(blockData, i);
            }
        }
	}


	private static boolean repairItemBlock(ItemBlock item, Block newBlock) throws Exception {
		if(item == null){
			return false;
		}

		if(item instanceof ItemMultiTexture){
			ItemMultiTexture multi = (ItemMultiTexture) item;

			new Reflector(multi).setFieldValue("b", newBlock);
		}

		new Reflector(item).setFieldValue("a", newBlock);

		return true;
	}
}
