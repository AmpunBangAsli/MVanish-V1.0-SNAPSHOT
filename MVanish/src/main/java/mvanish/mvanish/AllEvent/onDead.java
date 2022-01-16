package mvanish.mvanish.AllEvent;

import mvanish.mvanish.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onDead implements Listener {
    public static Main plugin;

    public onDead(Main plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void onDead(PlayerDeathEvent e){
        if (plugin.invisible.contains(e.getEntity().getPlayer().getName())) {
            e.setDeathMessage(null);
        }
    }


}
