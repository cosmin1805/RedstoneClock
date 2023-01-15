package ro.iacobai.redstoneclock.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ro.iacobai.redstoneclock.commands.SubCommand;
import ro.iacobai.redstoneclock.models.Clock;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

public class ChangeCommand extends SubCommand {
    @Override
    public String getName() {
        return "change";
    }

    @Override
    public String getDescription() {
        return "change if the redstone clock is active or not";
    }

    @Override
    public String getSyntax() {
        return "/rc change <name>";
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
                    clock.setState(false);
                    player.sendMessage(clock.getName()+":"+ ChatColor.WHITE+" State: "+ChatColor.RED+"OFF");
                }else {
                    clock.setState(true);
                    player.sendMessage(clock.getName()+":"+ ChatColor.WHITE+" State: "+ChatColor.GREEN+"ON");
                }
                ClockStorageUtil.updateClock(args[1],player,clock);
            }
        }
        else {
            player.sendMessage(ChatColor.RED+"THE NAME NEEDS TO BE ONLY ONE WORD!");
        }
        player.sendMessage(ChatColor.GREEN+"---------------------");
    }
}
