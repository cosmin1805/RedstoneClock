package ro.iacobai.redstoneclock.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import ro.iacobai.redstoneclock.commands.SubCommand;
import ro.iacobai.redstoneclock.models.Clock;
import ro.iacobai.redstoneclock.utils.ClockConvertData;
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
            player.sendMessage(ChatColor.RED + getSyntax());
        }
        else if(args.length==2){
            Clock clock = ClockStorageUtil.findClock(args[1], player);
            if(clock == null){
                player.sendMessage(ChatColor.RED+"THIS REDSTONE CLOCK DOESN'T EXIST!");
            }
            else {
                Location location =  ClockConvertData.locationConvert(clock.getLocation());
                if(clock.getState()){
                    player.sendMessage(clock.getName()+": "+"Location: "+ChatColor.GOLD+"x:"+ChatColor.LIGHT_PURPLE+location.getX()+ChatColor.GOLD+" y:"+ChatColor.LIGHT_PURPLE+location.getY()+ChatColor.GOLD+" z:"+ChatColor.LIGHT_PURPLE+location.getZ()+ChatColor.WHITE+", World: "+ChatColor.LIGHT_PURPLE+location.getWorld().getName()+ChatColor.WHITE+", State: "+ChatColor.GREEN+"ON"+ChatColor.WHITE+", Delay: "+ChatColor.LIGHT_PURPLE+clock.getDelay()+ChatColor.WHITE+", Time on: "+ChatColor.LIGHT_PURPLE+clock.getTime_on());
                }
                else {
                    player.sendMessage(clock.getName()+": "+"Location: "+ChatColor.GOLD+"x:"+ChatColor.LIGHT_PURPLE+location.getX()+ChatColor.GOLD+" y:"+ChatColor.LIGHT_PURPLE+location.getY()+ChatColor.GOLD+" z:"+ChatColor.LIGHT_PURPLE+location.getZ()+ChatColor.WHITE+", World: "+ChatColor.LIGHT_PURPLE+location.getWorld().getName()+ChatColor.WHITE+", State: "+ChatColor.RED+"OFF"+ChatColor.WHITE+", Delay: "+ChatColor.LIGHT_PURPLE+clock.getDelay()+ChatColor.WHITE+", Time on: "+ChatColor.LIGHT_PURPLE+clock.getTime_on());
                }
            }
        }
        else {
            player.sendMessage(ChatColor.RED+"THE NAME NEEDS TO BE ONLY ONE WORD!");
        }
        player.sendMessage(ChatColor.GREEN+"---------------------");
    }
}
