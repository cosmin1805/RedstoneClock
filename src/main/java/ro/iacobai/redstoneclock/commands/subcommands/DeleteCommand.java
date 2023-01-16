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

public class DeleteCommand extends SubCommand {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "delete a redstone clock"+ ChatColor.RED+ "(!IT WONT ASK FOR A CONFIRMATION!)";
    }

    @Override
    public String getSyntax() {
        return "/rc delete <name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        player.sendMessage(ChatColor.GREEN+"---------------------");
        if(args.length==1){
            player.sendMessage(ChatColor.RED + getSyntax());
        }
        else if(args.length==2){
            Clock clock = ClockStorageUtil.findClock(args[1],player);
            if(ClockStorageUtil.deleteClock(args[1], player) == false){
                player.sendMessage(ChatColor.RED+"THIS REDSTONE CLOCK DOESN'T EXIST!");
            }
            else {
                if(clock.getState()){
                    int ID_REDSTONE = PlaceRedstoneBlock.getId(clock);
                    int ID_AIR = PlaceAirBlock.getId(clock);
                    Bukkit.getScheduler().cancelTask(ID_AIR);
                    Bukkit.getScheduler().cancelTask(ID_REDSTONE);
                    //WE MAKE SURE THE OLD REDSTONE CLOCK LOCATION IS SET TO AIR
                    Location clock_location = ClockConvertData.locationConvert(clock.getLocation());
                    clock_location.getBlock().setType(Material.AIR);
                }
                player.sendMessage(ChatColor.GREEN+"THE REDSTONE CLOCK HAS BEEN DELETED!");
            }
        }
        else {
            player.sendMessage(ChatColor.RED+"THE NAME NEEDS TO BE ONLY ONE WORD!");
        }
        player.sendMessage(ChatColor.GREEN+"---------------------");
    }
}
