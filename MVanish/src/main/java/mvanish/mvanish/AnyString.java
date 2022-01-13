package mvanish.mvanish;

import org.bukkit.ChatColor;

public class AnyString {

    Main plugin;

    public AnyString(Main plugin){
        this.plugin = plugin;
    }

    public String colorprefix() {
        return plugin.config.getString("prefix");
    }

    public String fixprefix() {
        return ChatColor.translateAlternateColorCodes('&', colorprefix());
    }
    public String coloractionbar() {
        return plugin.config.getString("actionbar");
    }

    public String fixactionbar() {
        return ChatColor.translateAlternateColorCodes('&', coloractionbar());
    }

    public String prefix = fixprefix();

    public String actionbar = fixactionbar();
}
