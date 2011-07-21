package me.ryall.scavenger.listeners;

import me.ryall.scavenger.Scavenger;
import me.ryall.scavenger.system.RestorationManager;

import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
            	
            	if (lastCause instanceof EntityDamageByEntityEvent){
            		
            		EntityDamageByEntityEvent eeeee = (EntityDamageByEntityEvent)event.getEntity().getLastDamageCause();
            		Entity lastDamager = eeeee.getDamager();
            		
            		if (lastDamager instanceof Player){
            			/* Killed in pvp */
            			
            			if (Scavenger.get().getPermissionManager().hasImmunityPermission(player)){
            			/* Skip restoration */
                			return;
            			}
            		}
            	}
		    
            	RestorationManager.collect((Player)event.getEntity(), event.getDrops());
            }
        }
    }
}
