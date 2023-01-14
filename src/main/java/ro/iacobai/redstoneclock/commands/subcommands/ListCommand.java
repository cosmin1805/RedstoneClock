package ro.iacobai.redstoneclock.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ro.iacobai.redstoneclock.commands.SubCommand;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

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
        player.sendMessage("Your current clock are:"+ ClockStorageUtil.listAllOwnerClocks(player));
        player.sendMessage(ChatColor.GREEN+"---------------------");
    }
}
