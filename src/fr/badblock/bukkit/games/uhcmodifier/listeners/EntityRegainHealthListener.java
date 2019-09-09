package fr.badblock.bukkit.games.uhcmodifier.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

public class EntityRegainHealthListener implements Listener {
	@EventHandler(ignoreCancelled=true)
	public void onEntityRegainHealth(final EntityRegainHealthEvent e) {
		if(e.getRegainReason() == RegainReason.SATIATED)
			e.setCancelled(true);
	}
}
