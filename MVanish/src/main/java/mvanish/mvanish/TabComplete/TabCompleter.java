package mvanish.mvanish.TabComplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args){

        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            List<String> completions = new ArrayList<>();
            completions.add("about");
            completions.add("author"); // lah
            completions.add("version");
            completions.add("reload");
            for (String s : completions){
                if(s.toLowerCase().startsWith(args[0].toLowerCase())){
                    commands.add(s); //dah ;v work?:v
                }
            }
            return commands;
        }
        return null;
    }
}
