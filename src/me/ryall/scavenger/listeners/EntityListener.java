package me.ryall.scavenger.listeners;

import me.ryall.scavenger.Scavenger;
import me.ryall.scavenger.system.RestorationManager;

import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
//import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityListener extends org.bukkit.event.entity.EntityListener
{
    public void onEntityDamage(EntityDamageEvent event)
    {
    }

    public void onEntityDeath(EntityDeathEvent event)
    {
        if (event.getEntity() instanceof Player)
        {
            Player player = (Player)event.getEntity();

            if (Scavenger.get().getPermissionManager().hasScavengePermission(player))
            {
                
            	/* Figure out what cause the damage */
            	EntityDamageEvent lastCause = event.getEntity().getLastDamageCause();
            	//DamageCause dmgCause = lastDamager.getCause();
            	
            	if (lastCause instanceof EntityDamageByEntityEvent){
            		Scavenger.get().logInfo("Entity killed Player " + player.getDisplayName());
            		
            		EntityDamageByEntityEvent eeeee = (EntityDamageByEntityEvent)event.getEntity().getLastDamageCause();
            		Entity lastDamager = eeeee.getDamager();
            		
            		if (lastDamager instanceof Player){
            			Scavenger.get().logInfo("Player killed by player");
            		}
            	}
		    
            	RestorationManager.collect((Player)event.getEntity(), event.getDrops());
            }
        }
    }
}
