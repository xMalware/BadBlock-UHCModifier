package fr.badblock.bukkit.games.uhcmodifier.listeners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import fr.badblock.bukkit.games.uhcmodifier.utils.CreatureType;

public class EntityDeathListener implements Listener {
	private Random random = new Random();
	
	private final Material food;
	
	{
		switch(random.nextInt(5)){
			case 0: food = Material.COOKED_BEEF; break;
			case 1: food = Material.COOKED_CHICKEN; break;
			case 2: food = Material.COOKED_MUTTON; break;
			case 3: food = Material.COOKED_RABBIT; break;
			default: food = Material.GRILLED_PORK;
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(EntityDeathEvent e){
		CreatureType type = CreatureType.getByBukkit(e.getEntityType());
		
		e.setDroppedExp(e.getDroppedExp() * 4);
		boolean hasIron = false, hasArrow = false, hasLeather = false;
		
		for(ItemStack item : e.getDrops()){
			switch(item.getType()){
				case INK_SACK:
					MaterialData data = item.getData();
					data.setData((byte) 0);
				
					item.setData(data);
				case RAW_BEEF: case RAW_CHICKEN: case MUTTON: case RABBIT: case PORK: case ROTTEN_FLESH:
					item.setType(food);
					item.setAmount(2 * item.getAmount());
				break;
				case FEATHER:
					item.setType(Material.ARROW);
				break;
				case WOOL:
					data = item.getData();
					data.setData((byte) 0);
					
					item.setData(data);
					item.setType(Material.STRING);
				break;
				case BOW:
					item.setDurability((short) 0);
				break;
				case CARROT:
					item.setType(Material.GOLDEN_CARROT);
				break;
				default: break;
			}
			
			hasIron    = hasIron    || item.getType() == Material.IRON_INGOT;
			hasArrow   = hasArrow   || item.getType() == Material.ARROW;
			hasLeather = hasLeather || item.getType() == Material.LEATHER;
		}

		if(type == null) return;
		
		if(!hasIron && type.isHostile()){
			e.getDrops().add(new ItemStack(Material.IRON_INGOT, 1));
		}
		
		if(!hasArrow && type.isHostile()){
			e.getDrops().add(new ItemStack(Material.IRON_INGOT, 1));
		}
		
		if (!type.isHostile())
		{
			e.getDrops().add(new ItemStack(Material.BOOK, 1));
		}
		
		if(!hasLeather && (type == CreatureType.SHEEP || type == CreatureType.PIG || type == CreatureType.COW)){
			e.getDrops().add(new ItemStack(Material.LEATHER, random.nextInt(1) + 1));
		}
		
		if(type == CreatureType.CREEPER){
			e.getDrops().add(new ItemStack(Material.POTION, 1, (byte) 8193));
		}
		
		if (type == CreatureType.WITCH)
		{
			e.getDrops().add(new ItemStack(Material.POTION, 1, (byte) 8201));
		}

		if (type == CreatureType.SPIDER)
		{
			e.getDrops().add(new ItemStack(Material.POTION, 1, (byte) 8261));
		}
		
		if (type == CreatureType.COW)
		{
		}
		
		if (type == CreatureType.SKELETON)
		{
			e.getDrops().add(new ItemStack(Material.POTION, 1, (byte) 8194));
		}
	}
}
