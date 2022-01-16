package mvanish.mvanish.Command;

import mvanish.mvanish.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.text.DecimalFormat;

public class About implements CommandExecutor {

    public static Main plugin;

    public About(Main plugin){
        this.plugin = plugin;
    }


    public static String colorprefix() {
        return plugin.getConfig().getString("prefix");
    }

    public static String prefix() {
        return colorprefix().replace("&", "§");
    }




    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("vanish-about")){
            if (args.length != 1) {
                sender.sendMessage(prefix() + "§cHow to use: /vanish-about [reload|version|about|author]");
            } else {
                if (args[0].equalsIgnoreCase("version")) {
                    sender.sendMessage(prefix() + "§6API Version:§e " + Bukkit.getVersion()); // build?;v
                } else if (args[0].equalsIgnoreCase("about")) {
                    sender.sendMessage(prefix() + "§6Simple vanish plugin for AmpunNetwork designed by AmpunBang_");
                } else if (args[0].equalsIgnoreCase("author")) {
                    sender.sendMessage(prefix() + "§6Author: §eAmpunBang_"); // yang version about sama author gk ush perms kali?:v, se7;v
                } else if (sender.hasPermission("mvanish.reload")) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        plugin.reloadConfig();
                        sender.sendMessage("§eReloaded Config!");
                    }
                } else {
                    sender.sendMessage(prefix() + "§cYou don't have required permission!");
                }




            }
        }
        return true;
    }
}
