package ro.iacobai.redstoneclock.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ro.iacobai.redstoneclock.commands.SubCommand;
import ro.iacobai.redstoneclock.models.Clock;
import ro.iacobai.redstoneclock.tasks.PlaceAirBlock;
import ro.iacobai.redstoneclock.tasks.PlaceRedstoneBlock;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

public class SetCommand extends SubCommand {

    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getDescription() {
        return "set the delay or the time on ofg the redstone clock";
    }

    @Override
    public String getSyntax() {
        return "/rc set <name> delay/time_on <time>";
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    @Override
    public void perform(Player player, String[] args) {
        if(args.length==2) {
            Clock clock = ClockStorageUtil.findClock(args[1], player);
            if (clock == null) {
                player.sendMessage(ChatColor.RED + "THIS REDSTONE CLOCK DOESN'T EXIST!");
                return;
            }
            if (args.length == 4) {
                player.sendMessage(ChatColor.GREEN + "---------------------");
                if (!args[2].equals("delay") && !args[2].equals("time_on")) {
                    player.sendMessage(ChatColor.RED + getSyntax());
                    player.sendMessage(ChatColor.GREEN + "---------------------");
                    return;
                }
                if (isNumeric(args[3])) {
                    int time = Integer.parseInt(args[3]);
                    if (time > 0) {
                        if (args[2].equals("delay")) {
                            clock.setDelay(time);
                            player.sendMessage(clock.getName() + ":" + ChatColor.WHITE + " Delay: " + ChatColor.LIGHT_PURPLE + time);
                        } else if (args[2].equals("time_on")) {
                            clock.setTime_on(time);
                            player.sendMessage(clock.getName() + ":" + ChatColor.WHITE + " Time_on: " + ChatColor.LIGHT_PURPLE + time);
                        }
                        player.sendMessage(ChatColor.GREEN + "---------------------");
                        ClockStorageUtil.updateClock(clock);
                        return;
                    }
                    player.sendMessage(ChatColor.RED + "ONLY POSITIVE NUMBERS ALLOWED!");
                    player.sendMessage(ChatColor.GREEN + "---------------------");
                    return;
                }
                player.sendMessage(ChatColor.RED + "ONLY NUMBERS ALLOWED!");
                player.sendMessage(ChatColor.GREEN + "---------------------");
                return;
            }
        }
        player.sendMessage(ChatColor.GREEN+"---------------------");
        player.sendMessage(ChatColor.RED + getSyntax());
        player.sendMessage(ChatColor.GREEN + "---------------------");
    }
}
