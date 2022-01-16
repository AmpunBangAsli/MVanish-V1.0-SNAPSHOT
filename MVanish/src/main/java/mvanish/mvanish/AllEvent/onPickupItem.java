package mvanish.mvanish.AllEvent;

import mvanish.mvanish.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class onPickupItem implements Listener {
    public static Main plugin;

    public onPickupItem(Main plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void onPickItem(PlayerPickupItemEvent e){
        if (plugin.invisible.contains(e.getPlayer().getName()) && plugin.getConfig().getBoolean("player-can-pickup-items") == false){
            e.setCancelled(true);
        }
    }

}
