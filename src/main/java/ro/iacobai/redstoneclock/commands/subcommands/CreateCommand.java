package ro.iacobai.redstoneclock.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ro.iacobai.redstoneclock.commands.SubCommand;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

public class CreateCommand extends SubCommand {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "create a redstone clock";
    }

    @Override
    public String getSyntax() {
        return "/rc create <name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        player.sendMessage(ChatColor.GREEN+"---------------------");
        if(args.length==1){
            player.sendMessage(ChatColor.RED + getSyntax());
        }
        else if(args.length==2){
            if(ClockStorageUtil.createClock(args[1],player)){
                player.sendMessage(ChatColor.GREEN+"THE REDSTONE CLOCK HAS BEEN CREATED!");
            }
            else {
                player.sendMessage(ChatColor.RED+"THE NAME IS ALREADY USED!");
            }
        }
        else {
            player.sendMessage(ChatColor.RED+"THE NAME NEEDS TO BE ONLY ONE WORD!");
        }
        player.sendMessage(ChatColor.GREEN+"---------------------");
    }
}
