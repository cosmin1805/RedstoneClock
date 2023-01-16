package ro.iacobai.redstoneclock.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import ro.iacobai.redstoneclock.models.Clock;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

import java.util.ArrayList;

public class BlockBreakEventListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Location location = event.getBlock().getLocation();
        ArrayList<Clock> clocks = ClockStorageUtil.listAllClocks();
        for(Clock clock: clocks){
            if(clock.getLocation().equals(location.toString()) && clock.getState()){
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED+"YOU CAN'T BREAK THIS BLOCK!");
            }
        }
    }
}
