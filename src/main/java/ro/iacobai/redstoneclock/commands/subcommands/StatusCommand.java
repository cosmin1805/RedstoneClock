package ro.iacobai.redstoneclock.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ro.iacobai.redstoneclock.commands.SubCommand;
import ro.iacobai.redstoneclock.models.Clock;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

public class StatusCommand extends SubCommand {
    @Override
    public String getName() {
        return "status";
    }

    @Override
    public String getDescription() {
        return "get the status of a redstone clock";
    }

    @Override
    public String getSyntax() {
        return "/rc status <name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        player.sendMessage(ChatColor.GREEN+"---------------------");
        if(args.length==1){
            player.sendMessage(ChatColor.RED+"YOU NEED TO PROVIDE A NAME!");
        }
        else if(args.length==2){
            Clock clock = ClockStorageUtil.findClock(args[1], player);
            if(clock == null){
                player.sendMessage(ChatColor.RED+"THIS REDSTONE CLOCK DOESN'T EXIST!");
            }
            else {
                if(clock.getState()){
                    player.sendMessage(clock.getName()+": "+"Location: "+ChatColor.LIGHT_PURPLE+clock.getLocation()+ChatColor.WHITE+", State: "+ChatColor.GREEN+"ON"+ChatColor.WHITE+", Delay: "+ChatColor.LIGHT_PURPLE+clock.getTime());
                }
                else {
                    player.sendMessage(clock.getName()+": "+"Location: "+ChatColor.LIGHT_PURPLE+clock.getLocation()+ChatColor.WHITE+", State: "+ChatColor.RED+"OFF"+ChatColor.WHITE+", Delay: "+ChatColor.LIGHT_PURPLE+clock.getTime());
                }
            }
        }
        else {
            player.sendMessage(ChatColor.RED+"THE NAME NEEDS TO BE ONLY ONE WORD!");
        }
        player.sendMessage(ChatColor.GREEN+"---------------------");
    }
}
