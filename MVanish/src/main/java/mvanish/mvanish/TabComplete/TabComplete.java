package mvanish.mvanish.TabComplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        if(args.length == 1){
            List<String> completions = new ArrayList<>();

            completions.add("list");

            for (String s : completions){
                if (s.toLowerCase().startsWith(args[0].toLowerCase())){
                    commands.add(s);
                }
            }
            return commands;
        }
        return null;
    }
}
