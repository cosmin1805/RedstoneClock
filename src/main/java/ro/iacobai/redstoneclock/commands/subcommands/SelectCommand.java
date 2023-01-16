package ro.iacobai.redstoneclock.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ro.iacobai.redstoneclock.RedstoneClock;
import ro.iacobai.redstoneclock.commands.SubCommand;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

import java.util.HashMap;
import java.util.UUID;

public class SelectCommand extends SubCommand {
    private static HashMap<UUID, String> Players_Selected_clock =new HashMap<>();
    public static String getSelected_Clock(Player player) {
        if(Players_Selected_clock.containsKey(player.getUniqueId())){
            return Players_Selected_clock.get(player.getUniqueId());
        }else {
           return null;
        }
    }
    public static void setSelected_Clock(Player player,String name) {
        Players_Selected_clock.put(player.getUniqueId(),name);
    }
    @Override
    public String getName() {
        return "select";
    }

    @Override
    public String getDescription() {
        return "select the clock you want to modify its location";
    }

    @Override
    public String getSyntax() {
        return "/rc select <name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        player.sendMessage(ChatColor.GREEN+"---------------------");
        if(args.length==1){
            if(getSelected_Clock(player) ==null){
                player.sendMessage(ChatColor.RED+"YOU NEED TO PROVIDE A NAME!");
            }
            else {
                SelectCommand.setSelected_Clock(player,null);
                player.sendMessage(ChatColor.GREEN+"YOUR SELECTION HAS BEEN RESET");
            }

        }
        else if(args.length==2){
            if(ClockStorageUtil.findClock(args[1], player) == null){
                player.sendMessage(ChatColor.RED+"THIS REDSTONE CLOCK DOESN'T EXIST!");
            }
            else {
                setSelected_Clock(player,args[1]);
                player.sendMessage(ChatColor.WHITE+"THE REDSTONE CLOCK: "+ChatColor.LIGHT_PURPLE+args[1]+ChatColor.WHITE+" HAS BEEN SELECTED");
                player.sendMessage(ChatColor.WHITE+"TO CANCEL YOUR SELECTION TYPE /rc select");

            }
        }
        else {
            player.sendMessage(ChatColor.RED+"THE NAME NEEDS TO BE ONLY ONE WORD!");
        }
        player.sendMessage(ChatColor.GREEN+"---------------------");
    }
}
