package mvanish.mvanish.AllEvent;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class onStepingFarmLand implements Listener {

    @EventHandler
    public void onStepFarmLand(PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.FARMLAND) {
            e.setCancelled(true);
        }
    }
}
