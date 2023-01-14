package ro.iacobai.redstoneclock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class TabComplete implements TabCompleter {
    @Override
    public  List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==1){
            List<String> tabs =Arrays.asList();
            return tabs;
        }
        return null;
    }
}
