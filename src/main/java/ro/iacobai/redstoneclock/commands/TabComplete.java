package ro.iacobai.redstoneclock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import ro.iacobai.redstoneclock.models.Clock;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabComplete implements TabCompleter {
    @Override
    public  List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(args.length==1){
            List<String> tabs =Arrays.asList("create","set","delete","list","select","status","state");
            return tabs;
        }
        else if(args.length==2){
            List<String> allowed_tabs =Arrays.asList("set","delete","select","status","state");
            for(String tab : allowed_tabs){
                if(tab.equals(args[0])){
                    List<String> tabs =new ArrayList<>();
                    ArrayList<Clock> clocks =  ClockStorageUtil.listAllOwnerClocks(player);
                    for (Clock clock : clocks){
                        tabs.add(clock.getName());
                    }
                    return tabs;
                }
            }
        }
        else if(args.length== 3 && args[0].equals("set")){
            List<String> tabs =Arrays.asList("delay","time_on");
            return tabs;
        }
        List<String> tabs =Arrays.asList("");
        return tabs;
    }
}
