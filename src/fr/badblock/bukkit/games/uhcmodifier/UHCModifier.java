package fr.badblock.bukkit.games.uhcmodifier;

import java.io.File;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.badblock.bukkit.games.uhcmodifier.listeners.BlockBreakListener;
import fr.badblock.bukkit.games.uhcmodifier.listeners.EntityDamageByEntityListener;
import fr.badblock.bukkit.games.uhcmodifier.listeners.EntityDeathListener;
import fr.badblock.bukkit.games.uhcmodifier.listeners.EntityRegainHealthListener;
import fr.badblock.bukkit.games.uhcmodifier.listeners.PrepareItemCraftListener;
import fr.badblock.bukkit.games.uhcmodifier.overrided_blocks.OBlocks;
import fr.badblock.bukkit.games.uhcmodifier.overrided_items.OItems;
import fr.badblock.minecraftserver.JsonUtils;

public class UHCModifier extends JavaPlugin {
	public static UHCModifier 			 instance;
	public static UModifierConfiguration config;
	
	@Override
	public void onLoad(){
		instance = this;
		
		try {
			File f = new File(getDataFolder(), "config.json");
			
			config = JsonUtils.load( f, UModifierConfiguration.class );
			JsonUtils.save(f, config, true);
			
			MaxItemSize.doJob();
			RecipeModifier.doJob();
			
			if(config.modifyBlocksAndItems){
				OBlocks.register();
				OItems.register();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new EntityDamageByEntityListener(), this);
		pm.registerEvents(new EntityRegainHealthListener(), this);
		pm.registerEvents(new EntityDeathListener(), this);
		pm.registerEvents(new BlockBreakListener(), this);
		pm.registerEvents(new PrepareItemCraftListener(), this);
	}
}
