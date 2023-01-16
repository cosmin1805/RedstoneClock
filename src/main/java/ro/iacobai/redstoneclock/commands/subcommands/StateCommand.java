package ro.iacobai.redstoneclock.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import ro.iacobai.redstoneclock.commands.SubCommand;
import ro.iacobai.redstoneclock.models.Clock;
import ro.iacobai.redstoneclock.tasks.PlaceAirBlock;
import ro.iacobai.redstoneclock.tasks.PlaceRedstoneBlock;
import ro.iacobai.redstoneclock.utils.ClockConvertData;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

public class StateCommand extends SubCommand {
    @Override
    public String getName() {
        return "state";
    }

    @Override
    public String getDescription() {
        return "change if the redstone clock is active or not";
    }

    @Override
    public String getSyntax() {
        return "/rc state <name>";
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
                if(clock.getState()){
                    clock.setState(false);
                    player.sendMessage(clock.getName()+":"+ ChatColor.WHITE+" State: "+ChatColor.RED+"OFF");
                    int ID_REDSTONE = PlaceRedstoneBlock.getId(clock);
                    int ID_AIR = PlaceAirBlock.getId(clock);
                    Bukkit.getScheduler().cancelTask(ID_AIR);
                    Bukkit.getScheduler().cancelTask(ID_REDSTONE);
                    //WE MAKE SURE THE OLD REDSTONE CLOCK LOCATION IS SET TO AIR
                    Location clock_location = ClockConvertData.locationConvert(clock.getLocation());
                    clock_location.getBlock().setType(Material.AIR);
                }else {
                    clock.setState(true);
                    player.sendMessage(clock.getName()+":"+ ChatColor.WHITE+" State: "+ChatColor.GREEN+"ON");
                    PlaceRedstoneBlock.run_task(clock);
                }
                ClockStorageUtil.updateClock(clock);
            }
        }
        else {
            player.sendMessage(ChatColor.RED+"THE NAME NEEDS TO BE ONLY ONE WORD!");
        }
        player.sendMessage(ChatColor.GREEN+"---------------------");
    }
}
