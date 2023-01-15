package ro.iacobai.redstoneclock.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ro.iacobai.redstoneclock.commands.SubCommand;
import ro.iacobai.redstoneclock.models.Clock;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

import java.util.ArrayList;

public class ListCommand extends SubCommand {

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "lists all the redstone clocks";
    }

    @Override
    public String getSyntax() {
        return "rc list";
    }

    @Override
    public void perform(Player player, String[] args) {
        player.sendMessage(ChatColor.GREEN+"---------------------");
        String list = "";
        for (Clock clock : ClockStorageUtil.listAllOwnerClocks(player)){
            list+=" "+ChatColor.LIGHT_PURPLE+clock.getName()+ ChatColor.WHITE+",";
        }
        if(list!=""){
            list = list.substring(0,list.length()-1);
        }
        player.sendMessage("Your current clock are:"+list);
        player.sendMessage(ChatColor.GREEN+"---------------------");
    }
}
