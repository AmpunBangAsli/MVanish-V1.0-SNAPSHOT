package mvanish.mvanish;

import mvanish.mvanish.AllEvent.onDead;
import mvanish.mvanish.AllEvent.onMobsHit;
import mvanish.mvanish.AllEvent.onPickupItem;
import mvanish.mvanish.AllEvent.onStepingFarmLand;
import mvanish.mvanish.Command.About;
import mvanish.mvanish.Command.MainCommand;
import mvanish.mvanish.TabComplete.TabComplete;
import mvanish.mvanish.TabComplete.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Main extends JavaPlugin {
    public List<String> invisible;
    FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        invisible = getConfig().getStringList("vanished");
        config.addDefault("prefix", "&e&lVANISH &2/ &6");
        config.addDefault("actionbar", "&7> &e&lVANISHED&7 <");
        config.addDefault("player-can-pickup-items", true);
        config.addDefault("player-get-invisiblepotion-when-vanish", false);
        config.addDefault("player-get-nightvision-when-vanish", false);
        config.addDefault("joinmessage", "&e%PLAYER% joined the game!");
        config.addDefault("leavemessage", "&e%PLAYER% left the game!");
        config.addDefault("enablejoinandleavemessage", false);
        this.getCommand("vanish").setExecutor(new MainCommand(this));
        this.getCommand("vanish-other").setExecutor(new MainCommand(this));
        this.getCommand("vanish-about").setExecutor(new About(this));
        this.getCommand("unvanish").setExecutor(new MainCommand(this));
        this.getCommand("unvanish-other").setExecutor(new MainCommand(this));
        this.getCommand("vanish").setTabCompleter(new TabComplete());
        this.getCommand("vanish-about").setTabCompleter(new TabCompleter());
        config.options().copyDefaults(true);
        saveConfig();
        this.getServer().getPluginManager().registerEvents(new MainCommand(this), this);
        getServer().getPluginManager().registerEvents(new onStepingFarmLand(this), this);
       // getServer().getPluginManager().registerEvents(new onOpeningChest(this), this);
        getServer().getPluginManager().registerEvents(new onPickupItem(this), this);
        this.getServer().getPluginManager().registerEvents(new onDead(this), this);
        this.getServer().getPluginManager().registerEvents(new onMobsHit(this), this);
        System.out.println("§a|------------------------------------------------|");
        System.out.println("§e§lMVanish V1.0-SNAPSHOT §2/ §6Hello!");
        System.out.println("§a|------------------------------------------------|");
        System.out.println("§e§lMVanish V1.0-SNAPSHOT §2/ §6Waiting For Hook...");
        System.out.println("§e§lMVanish V1.0-SNAPSHOT §2/ §6Hook Connected!");

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null){
            System.out.println("§e§lMVanish V1.0-SNAPSHOT §2/ §cPlaceholderAPI not found!, please add PlaceholderAPI to your server!");
        }

    }

    @Override
    public void onDisable() {
        getConfig().set("vanished", invisible);
        saveConfig();
        System.out.println("§a|------------------------------------------------|");
        System.out.println("§e§lMVanish V1.0-SNAPSHOT §2/ §6See ya later!");
        System.out.println("§a|------------------------------------------------|");
        System.out.println("§e§lMVanish V1.0-SNAPSHOT §2/ §6Waiting For Hook Manager...");
        System.out.println("§e§lMVanish V1.0-SNAPSHOT §2/ §6Cleaning up all Hooks!");

    }
}
