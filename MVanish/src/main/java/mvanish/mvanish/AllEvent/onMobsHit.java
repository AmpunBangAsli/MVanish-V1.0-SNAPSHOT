package mvanish.mvanish.AllEvent;

import mvanish.mvanish.Main;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class onMobsHit implements Listener {

    public static Main plugin;

    public onMobsHit(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobHit(EntityDamageByEntityEvent e) {
        if (plugin.invisible.contains(e.getEntity().getName())) {
            if (!e.getDamager().getType().equals(EntityType.PLAYER)) {
                e.setCancelled(true);
            }
        }
    }


}
