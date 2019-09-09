package fr.badblock.bukkit.games.uhcmodifier.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import fr.badblock.bukkit.games.uhcmodifier.UHCModifier;
import fr.badblock.bukkit.games.uhcmodifier.utils.CreatureType;

public class EntityDamageByEntityListener implements Listener {
	@EventHandler(ignoreCancelled=true)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
		CreatureType damaged = CreatureType.getByBukkitEntity(e.getEntity());

		if(UHCModifier.config.oneShotAnimals && damaged != null && damaged.isFriendly() && isPlayer(e.getDamager())){
			e.setDamage(1000.0d);
		}
	}
	
	private boolean isPlayer(Entity entity){
		return entity.getType() == EntityType.PLAYER || (entity instanceof Projectile && isPlayer( ((Projectile) entity).getShooter() ));
	}
	
	private boolean isPlayer(ProjectileSource entity){
		return entity instanceof Player;
	}
}
