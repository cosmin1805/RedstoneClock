package ro.iacobai.redstoneclock.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ro.iacobai.redstoneclock.commands.SubCommand;
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
            player.sendMessage(ChatColor.RED+"YOU NEED TO PROVIDE A NAME!");
        }
        else if(args.length==2){
            if(ClockStorageUtil.findClock(args[1], player) == null){
                player.sendMessage(ChatColor.RED+"THIS REDSTONE CLOCK DOESN'T EXIST!");
            }
            else {
                ClockStorageUtil.deleteClock(args[1], player);
                player.sendMessage(ChatColor.GREEN+"THE REDSTONE CLOCK HAS BEEN DELETED!");
            }
        }
        else {
            player.sendMessage(ChatColor.RED+"THE NAME NEEDS TO BE ONLY ONE WORD!");
        }
        player.sendMessage(ChatColor.GREEN+"---------------------");
    }
}
