package mvanish.mvanish.AllEvent;

import mvanish.mvanish.Main;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class onOpeningChest implements Listener {

    /**

    public static Main plugin;

    public onOpeningChest(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onOpeningChest(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (plugin.invisible.contains(p.getName())) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getClickedBlock().getState() instanceof Chest) {
                    Chest chest = (Chest) e.getClickedBlock().getState();
                    Inventory inv = Bukkit.getServer().createInventory(null, InventoryType.CHEST);
                    for (ItemStack is : chest.getInventory().getContents()) {
                        inv.addItem(is);
                        chest.update();
                        p.openInventory(inv);
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void noLag2(InventoryClickEvent event){
        if (plugin.invisible.contains(event.getView().getPlayer().getName())){
            event.setCancelled(true);
        }
    }

    **/

}
