package mvanish.mvanish.Command;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import mvanish.mvanish.AnyString;
import mvanish.mvanish.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import static org.bukkit.Bukkit.getServer;

public class MainCommand implements CommandExecutor, Listener {

    public static Main plugin;

    public MainCommand(Main plugin){
        this.plugin = plugin;
    }

    public static String colorprefix() {
        return plugin.getConfig().getString("prefix");
    }

    public static String prefix() {
        return colorprefix().replace("&", "§");
    }

    public static String joincolorprefix() {
        return plugin.getConfig().getString("joinmessage");
    }

    public static String joinprefix() {
        return joincolorprefix().replace("&", "§");
    }

    public static String coloractionbar() {
        return plugin.getConfig().getString("actionbar");
    }

    public static String actionbar() {
        return coloractionbar().replace("&", "§");
    }

    /**
     * Simple Methode For Enable Vanish
     *
     *
     */

    public void enableVanish(Player player){
        for (Player all : getServer().getOnlinePlayers()){
            if (all.hasPermission("mvanish.showvanished")){
                if (all != player) {
                    all.sendMessage(prefix() + "§7" + player.getName() + " has Vanished!");
                    continue;
                }

            }
            all.hidePlayer(player);

        }
        if (plugin.getConfig().getBoolean("player-get-invisiblepotion-when-vanish") == true){
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 255, true, false));
        }

        if (plugin.getConfig().getBoolean("enablejoinandleavemessage") == true){
            String message = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("leavemessage"));
            message = message.replace("%PLAYER%", player.getName());
            Bukkit.broadcastMessage(message);
        }
        System.out.println(prefix() + "§7" + player.getName() + " has Vanished!");
        player.sendMessage(prefix() + "You are currently Vanished!");
    }

    public void enableVanishOther(Player player){
        for (Player all : getServer().getOnlinePlayers()){
            if (all.hasPermission("mvanish.showvanished")){
                if (all != player) {
                    all.sendMessage(prefix() + "§7" + player.getName() + " has Vanished!");
                    continue;
                }
            }
            all.hidePlayer(player);



        }
        if (plugin.getConfig().getBoolean("player-get-invisiblepotion-when-vanish") == true){
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 255, true, false));
        }
        if (plugin.getConfig().getBoolean("enablejoinandleavemessage") == true){
            String message = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("leavemessage"));
            message = message.replace("%PLAYER%", player.getName());
            Bukkit.broadcastMessage(message);
        }
        System.out.println(prefix() + "§7" + player.getName() + " has Vanished!");
        player.sendMessage(prefix() + "§6One of the admins makes you vanish!");
    }

    /**
     *
     * Simple Methode For Disable Vanish
     *
     */

    public void disableVanish(Player player) {
        if (plugin.invisible.remove(player.getName())) {
            for (Player other : getServer().getOnlinePlayers()) {
                if (other.hasPermission("mvanish.showvanished")){
                    if (other != player) {
                        other.sendMessage(prefix() + "§7" + player.getName() + " no longer Vanish!");
                        continue;
                    }
                }
                other.showPlayer(player);

            }
            for (PotionEffect effect : player.getActivePotionEffects()){
                player.removePotionEffect(effect.getType());
            }
            if (plugin.getConfig().getBoolean("enablejoinandleavemessage") == true){
                String message = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("joinmessage"));
                message = message.replace("%PLAYER%", player.getName());
                Bukkit.broadcastMessage(message);
            }
            System.out.println(prefix() + "§7" + player.getName() + " no longer vanish!");
            player.sendMessage(prefix() + "You have reappeared!");
        } else {
            player.sendMessage(prefix() + "§cYou are not vanished!");
        }
    }

    public void disableVanishOther(Player player) {
        if (plugin.invisible.remove(player.getName())) {
            for (Player other : getServer().getOnlinePlayers()) {
                if (other.hasPermission("mvanish.showvanished")){
                    if (other != player) {
                        other.sendMessage(prefix() + "§7" + player.getName() + " no longer Vanish!");
                        continue;
                    }

                }
                other.showPlayer(player);

            }
            for (PotionEffect effect : player.getActivePotionEffects()){
                player.removePotionEffect(effect.getType());
            }
            if (plugin.getConfig().getBoolean("enablejoinandleavemessage") == true){
                String message = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("joinmessage"));
                message = message.replace("%PLAYER%", player.getName());
                Bukkit.broadcastMessage(message);
            }
            System.out.println(prefix() + "§7" + player.getName() + " no longer vanish!");
            player.sendMessage(prefix() + "§6You are no longer vanished!");
        }
    }

    /**
     *
     *
     * Show All Vanished Player
     *
     *
     */
    public void showVanishList(Player player) {
        String result = "";
        boolean first = true;
        for (String hidden : plugin.invisible) {
            if (getServer().getPlayerExact(hidden) == null)
                continue;

            if (first) {
                result += hidden;
                first = false;
                continue;
            }

            result += ", " + hidden;
        }

        if (result.length() == 0)
            player.sendMessage(prefix() + "All players are visible!");
        else
            player.sendMessage(prefix() + "Vanished players: §7" + result);
    }

    /**
     *
     *
     * Vanish command
     *
     */

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String name, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix() + "§cOnly Player can do this command!");
            return true;
        }

        Player player = (Player) sender;


        if (command.getName().equalsIgnoreCase("vanish")) {
            if (player.hasPermission("mvanish.vanish")){
                if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
                    showVanishList(player);
                } else {
                    if (plugin.invisible.contains(player.getName())) {
                        disableVanish(player);
                    } else {
                        plugin.invisible.add(player.getName());
                        enableVanish(player);
                        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
                            @Override
                            public void run() {
                                if (plugin.invisible.contains(player.getName())) {
                                    String message = actionbar();
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                                }
                            }
                        }, 0L, 0L);
                    }
                }
            } else {
                player.sendMessage(prefix() + "§cYou don't have required permission!");
            }
        } else if (command.getName().equalsIgnoreCase("vanish-other")) {
            if (player.hasPermission("mvanish.vanishother")) {
                Player target = Bukkit.getPlayer(args[0]);
                if (args.length == 1) {
                    if (target != null) {
                        if (plugin.invisible.contains(target.getName())) {
                            disableVanishOther(target);
                        } else {
                            plugin.invisible.add(target.getName());
                            enableVanishOther(target);
                        }
                    } else {
                        player.sendMessage(prefix() + "§cPlayer is not online!");
                    }
                } else {
                    sender.sendMessage(prefix() + "§cHow to use: /vanish-other <player>");
                }
            } else {
                player.sendMessage(prefix() + "§cYou don't have required permission!");
            }
        } else if (command.getName().equalsIgnoreCase("unvanish")){
            if (player.hasPermission("mvanish.unvanish")){
                if (plugin.invisible.contains(player.getName())){
                    disableVanish(player);
                } else {
                    player.sendMessage(prefix() + "§cYou are not vanished! §7(if you thinks this is bug please report!)");
                }
            } else {
                player.sendMessage(prefix() + "§cYou don't have required permission!");
            }
        } else if (command.getName().equalsIgnoreCase("unvanish-other")){

            if(args.length == 0){
                sender.sendMessage(prefix() + "§cHow to use: /unvanish-other <player>");
                return true;
            }
            if (player.hasPermission("mvanish.unvanishother")){
                Player target = Bukkit.getPlayer(args[0]);
                if (plugin.invisible.contains(target.getName())){
                    disableVanishOther(target);
                    sender.sendMessage(prefix() + "§6He are no longer vanish!");
                } else {
                    sender.sendMessage(prefix() + "§cHe are not vanished! §7(if you thinks this is bug please report!)");
                }
            } else {
                sender.sendMessage(prefix() + "§cYou don't have required permission!");
            }
        }

        return true;
    }


    /**
     *
     * On Player Join Event(make player still invis if use /v before) & sending join message to player(HAVE PERMISSION)
     *
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.invisible.contains(event.getPlayer().getName())) {
            enableVanish(event.getPlayer());
            for (Player p : getServer().getOnlinePlayers()) {
                if (p.hasPermission("mvanish.vanish")) {
                    if (p != event.getPlayer()) {
                        p.sendMessage(prefix() + "§7" + event.getPlayer().getName() + " join silently!");
                        continue;
                    }
                }
            }
            System.out.println(prefix() + "§7" + event.getPlayer().getName() + " join silently!");
        }
        event.setJoinMessage(null);
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (plugin.invisible.contains(player.getName())) {
                    String message = actionbar();
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                }
            }
        }, 0L, 0L);

        if (event.getPlayer().hasPermission("simplevanish.showvanished"))
            return;

        for (String hidden : plugin.invisible) {
            Player hiddenPlayer = getServer().getPlayerExact(hidden);
            if (hiddenPlayer != null) {
                event.getPlayer().hidePlayer(hiddenPlayer);
            }
        }
    }


    /**
     *
     * OnQuit Event sending leave message to player(HAVE PERMISSION)
     *
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if (plugin.invisible.contains(e.getPlayer().getName())){
            for (Player p : getServer().getOnlinePlayers()){
                if (p.hasPermission("mvanish.vanish")){
                    p.sendMessage(prefix() + "§7" + e.getPlayer().getName() + " left silently!");
                }
            }
            System.out.println(prefix() + "§7" + e.getPlayer().getName() + " left silently!");
        }
        e.setQuitMessage(null);
    }

    /**
     *
     * Cancel Opening Chest(Not work)
     *
     */

    /**
     @EventHandler
     public void onRightClickChest(PlayerInteractEvent e){
     if (plugin.invisible.contains(e.getPlayer().getName())){
     if (e.getClickedBlock().getType().equals(Material.CHEST)){
     e.getPlayer().setGameMode(GameMode.SPECTATOR);
     Chest chest = (Chest) e.getClickedBlock().getState();
     e.getPlayer().openInventory(chest.getInventory());
     e.setCancelled(true);
     BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
     scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
     @Override
     public void run() {
     e.getPlayer().setGameMode(GameMode.CREATIVE);
     e.setCancelled(true);
     }
     }, 3 * 20);
     }
     }
     }
     */

}