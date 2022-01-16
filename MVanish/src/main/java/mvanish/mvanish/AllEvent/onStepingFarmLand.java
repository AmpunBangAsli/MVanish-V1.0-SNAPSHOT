package mvanish.mvanish.AllEvent;

import mvanish.mvanish.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class onStepingFarmLand implements Listener {


    public Main plugin;

    public onStepingFarmLand(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onStepFarmLand(PlayerInteractEvent e) {
        if (plugin.invisible.contains(e.getPlayer().getName())) {
            if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.FARMLAND) {
                e.setCancelled(true);
            }
        }
    }
}
